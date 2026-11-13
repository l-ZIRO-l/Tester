---
tags: [runflow/architecture]
status: active
---
# System Topology

```
┌─────────────────────────── BROWSER (React + TS) ───────────────────────────┐
│ Monaco │ Variables/Heap panel │ Flow graph (React Flow) │ Console           │
│        Playback controller: step N → line N → node N → heap snapshot N       │
└───────────────────────────────┬─────────────────────────────────────────────┘
                                │ POST /api/v1/visualize {source, stdin}
┌───────────────────────────────▼─────────────────────────────────────────────┐
│ API (Node/Fastify): validate, rate-limit, queue, spawn container, enforce    │
│ external watchdog, cap output, validate trace schema                         │
└───────────────────────────────┬─────────────────────────────────────────────┘
                                │ docker run --runtime=runsc  (1 per run)
┌───────────────────────────────▼─────────────────────────────────────────────┐
│ SANDBOX (gVisor, no network, read-only fs, caps dropped)                     │
│  tracer.jar:  parse → CFG → compile (in memory) → launch target JVM → JDI    │
│               trace → JSON on container stdout                               │
└───────────────────────────────┬─────────────────────────────────────────────┘
                                ▼
              JSON trace (CFG + steps)  →  browser replays it
```

## Layers (responsibility → note)
| Layer | Does | Note |
|---|---|---|
| Client | Layout, playback, rendering, input validation of trace | [[Frontend Architecture]] |
| API | Gatekeeping, orchestration, limits | [[API Design]], [[API Abuse Protection]] |
| Sandbox | **All** processing of untrusted source | [[Sandbox Hardening Spec]], [[ADR-003 All Untrusted Processing Inside Sandbox]] |
| Data contract | Versioned JSON | [[Trace JSON Contract]] |

## Changes vs. the original blueprint
- Static AST parsing moved **inside** the sandbox (parser on host would be an attack surface).
- No Redis warm pool in MVP ([[ADR-005 Cold-Start Containers for MVP]]).
- <250 ms latency target dropped; realistic targets in [[Test Strategy]].
- Trace delivered on a channel separate from user stdout ([[ADR-004 Trace Channel Separate From User Stdout]]).
