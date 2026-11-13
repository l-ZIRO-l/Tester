---
tags: [runflow/guardrail]
status: draft
---
# Execution Limits and Caps

> [!note] Single source of truth for numbers. **Initial values** — calibrated in Spike S4 ([[Phase 1 - Spikes]]), then frozen.

| Limit | Initial value | Enforced by | On exceed |
|---|---|---|---|
| Source size | 20 KB | client + API + tracer | `REJECTED` / 413 |
| Stdin size | 4 KB | client + API | 413 |
| Request body | 32 KB | API | 413 |
| Compile time | 5 s | tracer + external timer | `COMPILE_ERROR` (timeout msg) |
| Run wall-clock (incl. sleep) | 10 s total | **external watchdog** | `TIME_LIMIT_EXCEEDED` |
| Traced steps | 5,000 | tracer | `STEP_LIMIT_EXCEEDED` (partial trace) |
| Traced call depth | 64 frames | tracer | `RECURSION_DEPTH_LIMIT` |
| Program stdout | 64 KB | tracer + API | `OUTPUT_LIMIT_EXCEEDED` |
| Container stdout (trace) | 10 MB | API | kill + `SANDBOX_ERROR` |
| Trace JSON returned | 5 MB typical target, 10 MB hard | API | `OUTPUT_LIMIT_EXCEEDED` |
| Objects per snapshot | 500 | tracer | truncate + flag |
| Array/collection preview | 50 elements | tracer | `{"t":"more"}` |
| 2D preview | 20 × 20 | tracer | truncate |
| String preview | 200 chars | tracer | `trunc: true` |
| Call-tree nodes displayed | 300 | client | collapse |
| Container memory | 512 MB | Docker | OOM-kill → `MEMORY_LIMIT_EXCEEDED` |
| Target JVM heap | 64 MB | `-Xmx` | OOM exception reported |
| PIDs | 128 | Docker | thread creation fails |
| CPU | 1.0 | Docker | slower, not fatal |

## Rules
- **Wall-clock is enforced outside the sandbox** (single-line tight loops produce no step events).
- Stream/emit the trace incrementally where possible so a kill still yields a partial trace.
- Any new cap added → add a row here and a test in [[Adversarial Test Corpus]].
- The 1,000-step limit in the original blueprint is too low for DSA (bubble sort, `fib(8)`); 5,000 is the starting point. UI tip: "try a smaller input".
