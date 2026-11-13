---
tags: [runflow/architecture, runflow/modules]
status: active
---
# Module Map

> [!abstract] Idea
> Build each module alone, test it alone, then integrate. The **contracts** between modules are frozen first; everything behind a contract can change freely.

## Contracts (the seams)
| ID | Contract | Defined in |
|---|---|---|
| C1 | CFG JSON (nodes, edges, line ranges, pointer hints) | [[Trace JSON Contract]], [[CFG Model and Line Mapping]] |
| C2 | Trace JSON (steps, stack, heap, stdout) | [[Trace JSON Contract]], [[Heap Snapshot Model]] |
| C3 | API request/response | [[API Design]] |
| C4 | Sandbox I/O: source JSON on stdin → trace JSON on stdout | [[ADR-003 All Untrusted Processing Inside Sandbox]], [[ADR-004 Trace Channel Separate From User Stdout]] |

Rule: a contract change needs a schema update (`schemas/trace.schema.json`), fixture updates, and passing contract tests on **both** sides ([[Repo Structure]]).

## Dependency picture
```
BACKEND CHAIN                                   FRONTEND CHAIN
M1 Static analyzer ─┐                           M8 Trace player (TS lib)
M2 Compiler ────────┼─► M3 JDI tracer ─► M4 Heap normalizer ─► M5 Trace assembler ─► [C2 JSON]
                    │                                                                  │
                    └──────────────── C1 (CFG) ────────────────────────────────────────┤
                                                                                       ▼
M6 Sandbox runner (wraps M1–M5, C4) ─► M7 API (C3) ─────────────────────────► M8 ─► M9 UI panels ─► M10 Renderers
```

## Modules
### M1 — Static analyzer
- **In/Out:** Java source → CFG JSON (C1) + pointer hints
- **Stack:** JavaParser (Java 17 level)
- **Test alone:** golden CFG files for the CFG-focus programs ([[DSA Test Corpus]]); unsupported constructs become opaque action nodes
- **Done when:** all supported constructs covered ([[MVP Scope]]); never throws on valid Java
- **Phase:** [[Phase 1 - Spikes]] (S1), [[Phase 2 - Tracer CLI]]

### M2 — Compiler wrapper
- **In/Out:** source → in-memory classes **or** diagnostics (line, col, message)
- **Flags:** `-g -proc:none -implicit:none`
- **Test alone:** programs with known compile errors; multi-class single file; compile-bomb source (A16)
- **Done when:** diagnostics match `javac` output for the test set

### M3 — JDI tracer core
- **In/Out:** compiled classes → ordered raw events (line, call, return, exception, stdout)
- **Test alone:** run corpus and compare program stdout with plain `java`; step cap, depth cap, VM-death robustness
- **Done when:** no lost steps (step requests re-armed); behaves under limits ([[Execution Limits and Caps]])
- **Coupling note:** only M1↔M3 agree on node ids and branches; keep the interface to `node` + `viaEdge` per step ([[ADR-002 Line Mapping Strategy]])

### M4 — Heap normalizer
- **In/Out:** JDI object references → logical heap objects ([[Heap Snapshot Model]])
- **Test alone:** unit test per JDK type (`ArrayList`, `LinkedList`, `ArrayDeque`, `PriorityQueue`, `HashMap`, `TreeMap`, `Stack`) and user node classes; cycles; preview caps
- **Done when:** Spike S2 criteria met; shape hints correct ([[ADR-008 Structure-First Shape Detection]])

### M5 — Trace assembler
- **In/Out:** C1 + raw/normalized events → Trace JSON (C2) with keyframes every 25 steps + diffs
- **Test alone:** feed **fake events** (no JVM); check diff/keyframe reconstruction equals full snapshots; schema validation
- **Done when:** size caps respected; partial traces valid

### M6 — Sandbox runner
- **In/Out:** C4 in → C4 out, inside the container; wraps M1–M5
- **Test alone:** [[Adversarial Test Corpus]] through the real container; verify flags with `docker inspect`
- **Done when:** process isolation (S3) proven; no mounts needed ([[Sandbox Hardening Spec]])

### M7 — API orchestrator
- **In/Out:** HTTP (C3) → run M6 → validated trace
- **Test alone:** **mock M6** with a stub returning a fixture trace; test rate limits, queue, watchdog, byte caps ([[API Abuse Protection]])
- **Done when:** orphan containers = 0 after stress; every status maps correctly

### M8 — Trace player (TypeScript library, no UI)
- **In/Out:** C2 → `state(N)`: current line, active node, executed set, stack, locals, snapshot, call tree, console text
- **Test alone:** Vitest; step-back across keyframe boundaries equals forward state; zod validation rejects bad traces ([[Frontend Architecture]])
- **Done when:** random-access `state(N)` is exact on all golden traces

### M9 — UI panels
- **Panels:** editor, flow graph, variables/stack, console/input, timeline, banners ([[F01 Editor and Run]]–[[F04 Variables and Call Stack Panel]], [[F08 Console and Input]], [[F09 Errors and Exceptions UX]], [[F11 Workspace Layout]])
- **Test alone:** each panel against fixture traces (Storybook/Playwright); hostile-string fixtures inert ([[Frontend Safety]])

### M10 — Shape renderers (one mini-project each)
- **In/Out:** one logical heap object (+ pointer labels, changed set) → SVG
- **Renderers:** array, grid, string, stack, queue, linked list, tree, heap, map, graph, trie, raw ([[F05 Heap Renderer Catalog]])
- **Test alone:** standalone page with hand-written heap JSON; mutation sequences (swap, re-point, rotate); cycles; caps
- **Done when:** each renderer passes its acceptance list; raw fallback never crashes

## Build and integration order
1. Freeze C1 + C2; write fixtures (hand-written binary-search trace first).
2. Backend chain: M1 → M2 → M3 → M4 → M5 → CLI (`tracer.jar`).
3. Frontend chain (parallel or after): M8 → M9 → M10 (Tier 1 → 2 → 3).
4. **Integration 1:** real CLI traces → UI.
5. M6 sandbox, then M7 API.
6. **Integration 2:** UI → API → sandbox → UI. Run [[Adversarial Test Corpus]] on the full stack.

## Integration tests (at each seam)
| Seam | Test |
|---|---|
| M1↔M3 | node/branch attribution on CFG-focus programs ≥ 95% (S1) |
| M5→C2→M8 | tracer output validates against schema **and** loads in the player |
| M8↔M10 | every heap kind in the fixtures renders without error |
| M6↔M7 | C4 round trip with caps and kill paths |
| Full stack | corpus programs end-to-end; statuses and banners correct |

## Status board
| Module | Status |
|---|---|
| M1 Static analyzer | ⬜ |
| M2 Compiler wrapper | ⬜ |
| M3 JDI tracer core | ⬜ |
| M4 Heap normalizer | ⬜ |
| M5 Trace assembler | ⬜ |
| M6 Sandbox runner | ⬜ |
| M7 API orchestrator | ⬜ |
| M8 Trace player | ⬜ |
| M9 UI panels | ⬜ |
| M10 Shape renderers | ⬜ |

Roadmap mapping: [[Roadmap Overview]] · Overall status: [[Status Tracker]]
