---
tags: [runflow/adr]
status: accepted
---
# ADR-001 — Java 17 only for MVP

**Context:** Multi-language is a long-term goal, but each language needs its own parser, tracer, runtime and security model.
**Decision:** MVP supports **Java 17** only. Java dominates DSA courses; JDI gives industrial-grade inspection with static types.
**Consequences:** Hardest infrastructure (sandbox + tracing) is solved first; other languages later ([[Post-MVP Parking Lot]]). Heap logic depends on JDK 17 internals → pin the version ([[Heap Snapshot Model]]).
**Revisit when:** MVP Definition of Done met ([[MVP Definition of Done]]).
