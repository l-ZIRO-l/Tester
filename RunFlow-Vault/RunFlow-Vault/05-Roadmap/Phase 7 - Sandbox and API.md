---
tags: [runflow/phase]
status: draft
---
# Phase 7 — Sandbox and API

**Goal:** paste code in the UI → real sandboxed run → trace replays. Still private.

## Tasks
**Sandbox**
- [ ] Build minimal image (jlink) + digest pinning ([[Sandbox Hardening Spec]])
- [ ] Implement final process model from S3 (uid separation)
- [ ] `sandbox/run-sandbox.sh` with all flags; verify with `docker inspect`
- [ ] Entry shim: read JSON from stdin → tracer → trace on stdout
- [ ] Calibrated memory/PID/JVM flags ([[Execution Limits and Caps]])

**API**
- [ ] Fastify service, `/visualize`, `/health` ([[API Design]])
- [ ] Validation + body caps; error mapping to statuses
- [ ] Concurrency slots + bounded queue; external watchdog; byte-capped stdout reader
- [ ] Reaper job for orphaned containers
- [ ] Server-side schema validation of trace; size cap
- [ ] Structured logs (hash/timing only) ([[API Abuse Protection]])

**Integration**
- [ ] Web app calls API; spinner/elapsed; error mapping to [[F09 Errors and Exceptions UX]]
- [ ] Run the whole [[Adversarial Test Corpus]] through the real stack
- [ ] Latency measurements recorded vs targets in [[Test Strategy]]

## Exit criteria
All DSA corpus programs run end-to-end; adversarial corpus has **no host-impacting** outcome and correct statuses; orphan containers = 0 after stress.

Next: [[Phase 8 - Hardening and Launch]]
