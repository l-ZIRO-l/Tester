---
tags: [runflow/guardrail]
status: active
---
# Risk Register

Likelihood/Impact: L/M/H. Review at the end of every phase.

| ID | Risk | L | I | Mitigation | Owner phase | Status |
|---|---|---|---|---|---|---|
| R-01 | Line→node mapping can't determine branches reliably | M | H | Spike S1; probes (Option B); fallbacks | 1 | open |
| R-02 | Tracer/target isolation not achievable in one container | M | H | Spike S3 options A/B; no public launch without it | 1, 7 | open |
| R-03 | Docker socket = host root for orchestrator | M | H | Dedicated sandbox VM, unprivileged API, narrow spawner, patching | 7 | accepted w/ conditions |
| R-04 | gVisor/Docker escape 0-day | L | H | Defense in depth, no secrets on host, patching, kill switch | 8 | accepted |
| R-05 | JDK collection internals differ/unreadable | M | M | Pin Java 17; Spike S2; per-type tests | 1 | open |
| R-06 | JDI too slow → latency/timeouts | M | M | Calibrate S4; limit snapshots; exclude JDK; sample reads | 1 | open |
| R-07 | Traces too large | M | M | Keyframes+diffs, caps, gzip | 2 | open |
| R-08 | GC of tracked objects / memory blow-up from `disableCollection` | M | M | Release unreachable objects; test with allocation-heavy programs | 2 | open |
| R-09 | Shape detection misfires (list vs tree etc.) | M | M | Structure-first, back-edge rule, override dropdown | 5 | open |
| R-10 | Two parsers (JavaParser vs javac) disagree on Java 17 syntax | L | M | Compile with javac first; parse only if compile OK; unsupported → opaque nodes | 2 | open |
| R-11 | 5,000-step limit still too low for some DSA programs | M | L | UX tip, adjustable after S4, step sampling for long loops (post-MVP) | 4 | open |
| R-12 | XSS via user-derived strings | L | H | [[Frontend Safety]]; adversarial tests | 3 | open |
| R-13 | Abuse/cost spike | M | M | [[API Abuse Protection]]; kill switch | 8 | open |
| R-14 | Supply chain compromise | L | H | Pin, audit, scan | 0, 8 | open |
| R-15 | Scope creep (frameworks, languages) | H | M | [[MVP Scope]], [[Post-MVP Parking Lot]] | all | open |
| R-16 | Solo-dev burnout / big-bang build | M | H | Thin vertical slices; demoable at each phase | all | open |
| R-17 | Multithreaded programs confuse trace | L | L | Trace main only; detect threads, show note | 4 | open |
| R-18 | Nondeterminism confuses learners | L | L | UI note | 4 | open |
| R-19 | Accessibility (color-only state) | M | L | Icons/patterns in [[F03 Execution Flow Graph]] | 3 | open |
| R-20 | Competitor overlap (Python Tutor supports Java) | M | M | Re-check; focus on CFG + polish ([[Problem and Differentiator]]) | 0 | open |
| R-21 | Latency expectations (blueprint <250ms) unrealistic | H | L | Targets reset in [[Test Strategy]]; progress UI | 1 | mitigated |
