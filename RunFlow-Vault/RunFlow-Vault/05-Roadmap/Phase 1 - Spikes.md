---
tags: [runflow/phase, runflow/spike]
status: draft
---
# Phase 1 — Spikes (de-risking)

Time-box each spike (≈ 2–4 days). Write a result with [[Template Spike Report]]. Update ADRs and [[Risk Register]].

## S1 — Line→CFG-node and branch mapping (R-01)
**Question:** Can we attribute each runtime step to the right CFG node and branch?
- [ ] Build minimal JavaParser CFG for `if/else/while/for/return/break/continue`
- [ ] Prototype Option A (pure JDI) and Option B (condition probes) on the 10 CFG programs
- [ ] Compare outputs with plain `java` runs (behavior unchanged)
- [ ] Score vs acceptance criteria in [[CFG Model and Line Mapping]] (≥ 95%)
- [ ] Decide → finalize [[ADR-002 Line Mapping Strategy]]
**Fail plan:** reduce to line-level highlighting + node-per-line CFG, or move to Option C.

## S2 — Heap normalization (R-05)
**Question:** Can we read arrays, `ArrayList`, `LinkedList`, `ArrayDeque`, `PriorityQueue`, `HashMap`, `TreeMap`, `Stack`, and user nodes via JDI field reads only?
- [ ] Per-type extractor + unit test against real JDK 17
- [ ] Cycle-safe snapshot with caps; `disableCollection` strategy ([[Heap Snapshot Model]])
- [ ] Measure per-step cost and payload size
- [ ] Verify structure-first shape hints on corpus ([[ADR-008 Structure-First Shape Detection]])

## S3 — Tracer/target isolation (R-02)
**Question:** Can the target JVM run under a different uid from the tracer inside gVisor with caps dropped?
- [ ] Prototype Option A (`setpriv`) in the sandbox image; fall back to B
- [ ] Run A13/A14 from [[Adversarial Test Corpus]] (JDWP loopback poke, kill/ptrace/`/proc` access)
- [ ] Confirm code-in via stdin and trace-out via stdout work with no mounts
- [ ] Document final process model in [[Sandbox Hardening Spec]]

## S4 — Limits and latency (R-06, R-21)
**Question:** What limits are realistic?
- [ ] Measure: compile time, JVM start, per-step JDI cost, snapshot cost, trace size for corpus programs
- [ ] Choose JVM heap/stack, container memory, PID limit ([[Execution Limits and Caps]])
- [ ] Set latency targets in [[Test Strategy]]

## Exit criteria
All four spike reports written; [[ADR-002 Line Mapping Strategy]] **Accepted**; trace contract v1 **frozen** (update [[Trace JSON Contract]]); limits table updated; go/no-go decision recorded in [[Status Tracker]].

Next: [[Phase 2 - Tracer CLI]]
