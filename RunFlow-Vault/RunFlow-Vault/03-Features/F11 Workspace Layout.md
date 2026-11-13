---
tags: [runflow/feature]
status: draft
---
# F11 Workspace Layout

Matches the UI mockup (editor left; variables top-right; flow graph bottom-right; terminal drawer bottom).

```
┌ Top bar: Logo │ Run Flow │ ⏮ ◀ ▶/⏸ ▶ ⏭ │ speed │ Presets ▾ ┐
├──────────────────────┬────────────────────────────────────┤
│ Editor (Monaco)      │ Variables / Stack / Heap (tabs)    │
│ current line pointer ├────────────────────────────────────┤
│ error squiggles      │ Execution Flow Graph (React Flow)  │
├──────────────────────┴────────────────────────────────────┤
│ Drawer: Console │ Input   (collapsible)                    │
└───────────────────────────────────────────────────────────┘
```

## Behavior
- `react-resizable-panels`: draggable dividers, collapse/expand buttons, remembers sizes (localStorage, try/catch).
- Before first run, right panels show an empty state with "Press Run Flow" + preset suggestions; on run the canvas reveals smoothly.
- Minimum sizes prevent unusable panes; double-click a divider resets.
- Desktop-first (≥ 1100 px wide). Narrow screens → message "Best on a larger screen" (mobile is post-MVP).
- Dark theme only; WCAG AA contrast; focus rings; keyboard reachable controls.

## Acceptance
- [ ] Panels collapse/expand without layout glitches; Monaco reflows (`automaticLayout`)
- [ ] Sizes persist across reload
- [ ] Empty/loading/error states exist for every panel
