---
tags: [runflow/adr]
status: accepted
---
# ADR-004 — Trace channel separate from user stdout

**Context:** If the trace shares a stream with user output, user code can forge or corrupt it.
**Decision:** The **tracer's own stdout** (container stdout) carries only the trace JSON. The **target JVM's** stdout/stderr are read by the tracer via process streams and embedded as `stdoutAppend` per step. Source comes in via container stdin as JSON.
**Consequences:** no volumes/mounts needed; user output can never be parsed as trace; API caps container stdout by bytes.
**Note:** API still validates the trace schema (defense in depth).
Related: [[Threat Model]] T10; [[Sandbox Hardening Spec]].
