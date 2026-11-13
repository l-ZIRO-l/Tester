---
tags: [runflow/test]
status: draft
---
# Test Strategy

## Layers
| Layer | What | Tooling |
|---|---|---|
| Tracer unit | CFG builder, heap extractors, limits | JUnit |
| Golden traces | Corpus program → expected trace JSON, **hand-reviewed once**, then regression-guarded | JUnit + JSON diff |
| Behavior equivalence | Traced program stdout == plain `java` stdout | script in CI |
| Schema | Tracer output validates against `schemas/trace.schema.json` | CI |
| UI unit | reconstruct(), shape detection, selectors | Vitest |
| UI e2e | load fixture → play → step back → sync assertions; hostile strings inert | Playwright |
| Adversarial | [[Adversarial Test Corpus]] through real sandbox | script |
| Load/abuse | concurrency, queue, rate limits | k6 or autocannon |
| Visual review | manual screenshot checks per shape | checklist |

## Definition of "correct"
- **CFG**: each step's `node` and `viaEdge` match a hand-labeled expectation for corpus programs (≥ 95% in S1, target 100% on presets).
- **Heap**: logical kind and shape match expectation; cycles safe; caps respected.
- **Playback**: for any N, state(N) equals the state recorded at N (no drift after back-steps across keyframes).

## Performance targets (initial; reset after S4)
| Metric | Target |
|---|---|
| Run → first frame, ≤ 1,000 steps (p50) | ≤ 3 s |
| Run → first frame, 5,000 steps (p95) | ≤ 8 s |
| Trace size, typical preset | ≤ 500 KB (gzip) |
| Scrub through 5,000 steps | no frames > 50 ms (typical) |
| Panel resize | 60 fps feel |

> [!note] The blueprint's <250 ms target is dropped: JVM start + JDI per-step reads + gVisor make it unrealistic. A good progress UI beats a fake-fast promise ([[Risk Register]] R-21).

## When tests run
- Every PR: unit + schema + golden + UI unit.
- Nightly / before release: adversarial on real sandbox, e2e, load.
- Any bug found in the wild → add the program to a corpus.

Related: [[DSA Test Corpus]], [[Edge Case Checklist]], [[Public Launch Gate]].
