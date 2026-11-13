---
tags: [runflow/adr]
status: accepted
---
# ADR-005 — Cold-start containers for MVP

**Context:** Blueprint proposed a Redis-backed pre-warmed pool for <250 ms. That adds state, queueing, container-reuse risks, and a way to deliver code into network-less idle containers.
**Decision:** MVP uses **one fresh container per run**, cold start (~1–2 s). Concurrency slots + bounded queue in the API.
**Consequences:** simpler, safer (no cross-user state); slower first response (mitigated with progress UI). Revisit warm pools post-MVP with measured data ([[Post-MVP Parking Lot]]).
Related: [[API Design]], [[Sandbox Hardening Spec]], [[Risk Register]] R-21.
