---
tags: [runflow/phase]
status: draft
---
# Phase 4 — Tier 1 Visuals

**Topics:** arrays, 2D arrays/DP, strings, sorting/searching, recursion, stack/queue.

## Tasks
- [ ] Array 1D renderer + changed-cell highlight ([[F05 Heap Renderer Catalog]] #1)
- [ ] Pointer labels end-to-end (AST hints → tracer → UI) ([[F07 Pointer Labels]])
- [ ] 2D/DP grid renderer #2
- [ ] String/StringBuilder renderer #3
- [ ] Stack #4 and Queue/Deque #5 renderers (incl. JDK `Stack`, `ArrayDeque`, `Queue`/`Deque`, manual array stacks)
- [ ] Recursion call tree ([[F06 Recursion Call Tree]])
- [ ] "Render as" override dropdown (arrays/lists ↔ raw)
- [ ] Presets 1–6 ([[F10 Preset Library]]) with golden traces
- [ ] Differences/regressions suite passes ([[Test Strategy]])
- [ ] Handle thread detection note (R-17) and nondeterminism note (R-18)

## Exit criteria
Demo: bubble sort, binary search, `fib(4)` call tree, climbing-stairs DP grid, balanced parentheses stack — all correct, with pointer labels and highlights. Corpus T1 programs pass ([[DSA Test Corpus]]).

Next: [[Phase 5 - Tier 2 Visuals]]
