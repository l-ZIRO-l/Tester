---
tags: [runflow/feature]
status: draft
---
# F09 Errors and Exceptions UX

Turn failures into lessons.

| Situation | Status | UX |
|---|---|---|
| Syntax/type error | `COMPILE_ERROR` | Monaco squiggles + problems list (line, col, message); no run view |
| Uncaught runtime exception (NPE, AIOOBE, ArithmeticException, ClassCast…) | `RUNTIME_EXCEPTION` | Crash node turns **red** with badge; last step shows exception type, message, line; stack trace in console |
| Infinite loop / too many steps | `STEP_LIMIT_EXCEEDED` | Partial trace + banner "Stopped after N steps — possible infinite loop". Loop animating on the graph |
| Tight single-line loop / wall-clock | `TIME_LIMIT_EXCEEDED` | Banner "Took too long (Xs)". Partial trace if any |
| Too much output | `OUTPUT_LIMIT_EXCEEDED` | Banner; console truncated |
| Out of memory | `MEMORY_LIMIT_EXCEEDED` | Banner + hint about large allocations |
| Too deep recursion | `RECURSION_DEPTH_LIMIT` | Banner + call tree shows cap |
| Blocked API use | `REJECTED` | Friendly message: "`Runtime.exec` isn't allowed in RunFlow" |
| Our bug / sandbox failure | `SANDBOX_ERROR`/`INTERNAL_ERROR` | Generic apology + request id; details only in server logs |

## Rules
- Never show internal paths, container ids, or raw server errors.
- Every banner explains *what* happened and *what to try*.
- Partial traces are always playable.

## Acceptance
- [ ] Each status above has a UI test with a corpus program ([[Adversarial Test Corpus]], [[DSA Test Corpus]])
- [ ] Crash node correct for exceptions thrown inside nested calls
