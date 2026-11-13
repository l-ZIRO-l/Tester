---
tags: [runflow/phase]
status: draft
---
# Phase 3 — UI Shell on Recorded Traces

**Goal:** the complete workspace works by loading saved trace JSON (no backend).

## Tasks
- [ ] Layout with `react-resizable-panels`, collapse/expand, persisted sizes ([[F11 Workspace Layout]])
- [ ] Monaco editor, current-line decoration, markers for diagnostics ([[F01 Editor and Run]])
- [ ] Trace loader + **zod** validation + error boundaries ([[Frontend Architecture]], [[Frontend Safety]])
- [ ] Store + playback engine + timeline controls + keyboard ([[F02 Playback Controller]])
- [ ] Snapshot reconstruction (keyframes + diffs), memoization
- [ ] Flow graph: custom nodes (pill/diamond/box), Dagre layout, state styles, scope switcher, fit/center ([[F03 Execution Flow Graph]])
- [ ] Variables & call stack panel ([[F04 Variables and Call Stack Panel]])
- [ ] Console + Input tab (sanitized) ([[F08 Console and Input]])
- [ ] Error/limit banners for all statuses ([[F09 Errors and Exceptions UX]])
- [ ] Heap panel with **raw object graph fallback** + simple array view (to prove the pipeline)
- [ ] "Load trace JSON" dev tool (drag-drop) — **dev builds only**
- [ ] CSP + lint rules in place early
- [ ] Playwright smoke test: load fixture → play → step back → assert line/node/console sync

## Exit criteria
- Binary-search golden trace replays with correct line, node state, variables, console; step-back exact
- 5,000-step synthetic trace scrubs smoothly
- Hostile-string fixtures (A19–A22) render inert

Next: [[Phase 4 - Tier 1 Visuals]]
