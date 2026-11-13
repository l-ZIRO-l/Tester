---
tags: [runflow/adr]
status: accepted
---
# ADR-003 — All untrusted processing inside the sandbox

**Context:** The blueprint ran JavaParser on the API side "in parallel" with container allocation. Parsers can be crashed or stalled by hostile input (deep nesting → StackOverflow, pathological sizes).
**Decision:** The API only validates size/encoding and forwards source. **Parsing, CFG building, compiling and tracing all happen inside the sandbox** in one tracer jar.
**Consequences:** simpler API; one trust boundary; parser crashes cost one container, not the API. Slight latency cost; acceptable ([[Test Strategy]]).
Related: [[Threat Model]] T8, T17; [[Execution Pipeline]].
