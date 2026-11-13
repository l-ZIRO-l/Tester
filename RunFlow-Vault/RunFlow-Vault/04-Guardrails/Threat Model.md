---
tags: [runflow/guardrail]
status: draft
---
# Threat Model

## Assets
Host machine & its network/secrets · other users' data (none stored in MVP) · availability/cost · integrity of traces · users' browsers.

## Attackers
Curious students (accidental infinite loops) · script kiddies (fork bombs, exfil) · abusers (crypto mining, free compute, DoS) · targeted attackers (container escape, XSS via output).

## Entry points
`POST /visualize` body (source, stdin) · program output & values rendered in browser · compile step · JDI channel · docker/orchestrator host interface.

## Threats and mitigations
| # | Threat | Mitigation | Where |
|---|---|---|---|
| T1 | Container/kernel escape | gVisor `runsc`, no caps, `no-new-privileges`, dedicated sandbox host with no secrets, patch cadence | [[Sandbox Hardening Spec]] |
| T2 | User code attacks the tracer (same uid): kill, `/proc/<pid>/mem`, forge trace | Different uid for target; external watchdog; trace validated by API | [[Sandbox Hardening Spec]] (Spike S3) |
| T3 | Connect to JDWP/loopback to tamper | Target uid isolation; JDWP bound to loopback with ephemeral port; tracer treats target data as untrusted | S3, [[Test Strategy]] |
| T4 | Network exfil / SSRF / reverse shell | `--network none`; no metadata endpoint reachable | Sandbox spec |
| T5 | Fork bomb / thread bomb | `--pids-limit`, `ulimit`, thread caps | Sandbox spec |
| T6 | Memory bombs | JVM `-Xmx` + container memory; OOM mapped to status | [[Execution Limits and Caps]] |
| T7 | Infinite loop incl. single-line loops | External wall-clock kill + step cap | Limits |
| T8 | Compile bombs (deep nesting, huge generics); annotation processors | Size cap, compile timeout, `-proc:none`, compile inside sandbox | Pipeline |
| T9 | Output flooding (stdout/trace) | Byte caps at tracer **and** API; kill on overflow | Limits |
| T10 | Forged trace via printing JSON to stdout | Separate channel ([[ADR-004 Trace Channel Separate From User Stdout]]) | ADR-004 |
| T11 | JDI `invokeMethod` runs user code (hangs/deadlocks) | Field reads only | [[Heap Snapshot Model]] |
| T12 | XSS via output, strings, class names, labels, exception messages | Text-only rendering, CSP, schema validation, strip control chars | [[Frontend Safety]] |
| T13 | API abuse/DoS, pool exhaustion | Rate limits, concurrency & queue caps, body caps, optional CAPTCHA | [[API Abuse Protection]] |
| T14 | Docker socket = root on host | Isolated sandbox VM, API unprivileged, narrow spawner, no other services on host (accepted MVP risk R-03) | Sandbox spec |
| T15 | Supply chain (npm/maven/image) | Pin, lock, audit, scan image | [[Risk Register]] |
| T16 | Privacy: storing/leaking user code | Don't store source; hash-only logs | [[API Abuse Protection]] |
| T17 | Parser crash via StackOverflow on deeply nested source | Parser runs in sandbox with own limits | ADR-003 |
| T18 | Stale/malformed trace crashes UI | zod validation + error boundaries | [[Frontend Architecture]] |

## Residual risk (accepted for MVP)
gVisor/Docker 0-days; single sandbox host; no auth. Mitigated by launch gate, patching, monitoring, kill switch ([[Public Launch Gate]]).
