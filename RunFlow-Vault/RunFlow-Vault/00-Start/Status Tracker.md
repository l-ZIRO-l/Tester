---
tags: [runflow/status]
status: active
---
# Status Tracker

> [!info] Update this at the end of every working session.

**Current phase:** Phase 0 — Setup ([[Phase 0 - Setup]])
**Next action:** Create the repo skeleton and write the first 10 DSA test programs ([[DSA Test Corpus]]), then run Spike S1 ([[Phase 1 - Spikes]]).
**Blockers:** none

## Phase board
| Phase | Name | Status |
|---|---|---|
| 0 | [[Phase 0 - Setup]] | ⬜ not started |
| 1 | [[Phase 1 - Spikes]] | ⬜ |
| 2 | [[Phase 2 - Tracer CLI]] | ⬜ |
| 3 | [[Phase 3 - UI Shell]] | ⬜ |
| 4 | [[Phase 4 - Tier 1 Visuals]] | ⬜ |
| 5 | [[Phase 5 - Tier 2 Visuals]] | ⬜ |
| 6 | [[Phase 6 - Tier 3 Visuals]] | ⬜ |
| 7 | [[Phase 7 - Sandbox and API]] | ⬜ |
| 8 | [[Phase 8 - Hardening and Launch]] | ⬜ |

## Spike board
| Spike | Question | Result |
|---|---|---|
| S1 | Can we reliably map runtime steps to CFG nodes and branches? | ⬜ |
| S2 | Can we normalize JDK collections + user nodes from JDI? | ⬜ |
| S3 | Can the tracer be isolated from user code (different uid)? | ⬜ |
| S4 | What limits/latency are realistic? | ⬜ |

## Open decisions
- [[ADR-002 Line Mapping Strategy]] — Proposed (waits on S1)
- Process isolation option A/B/C — waits on S3 ([[Sandbox Hardening Spec]])

## Done log
_(add dated one-liners here)_
