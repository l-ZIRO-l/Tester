---
tags: [runflow/feature]
status: active
---
# Feature Index (MVP)

Priority: **P0** = MVP blocker · **P1** = MVP should-have. Tier = delivery order for visuals.

| ID | Feature | Pri | Tier | Phase |
|---|---|---|---|---|
| [[F01 Editor and Run]] | Monaco editor, Run Flow, draft autosave | P0 | – | 3 |
| [[F02 Playback Controller]] | Play/pause/step/scrub/speed/keys | P0 | – | 3 |
| [[F03 Execution Flow Graph]] | CFG with 4 states, scope switcher | P0 | – | 2–3 |
| [[F04 Variables and Call Stack Panel]] | Locals table + stack frames | P0 | – | 3 |
| [[F05 Heap Renderer Catalog]] | All DSA shapes + raw fallback | P0 | 1/2/3 | 4–6 |
| [[F06 Recursion Call Tree]] | Live call tree with return badges | P0 | 1 | 4 |
| [[F07 Pointer Labels]] | `i/j/low/mid/high` under cells | P0 | 1 | 4 |
| [[F08 Console and Input]] | stdout view + pre-filled stdin | P0 | – | 3 |
| [[F09 Errors and Exceptions UX]] | Compile squiggles, crash node, limit banners | P0 | – | 2–4 |
| [[F10 Preset Library]] | 8–10 starter programs | P1 | – | 4–6 |
| [[F11 Workspace Layout]] | Resizable/collapsible 4-pane | P0 | – | 3 |

## Tier map (DSA topics)
- **Tier 1** (Phase 4): arrays, 2D arrays, strings, sorting/searching, recursion, DP tables, stack/queue
- **Tier 2** (Phase 5): linked lists, trees/BST, heaps/PQ
- **Tier 3** (Phase 6): hash maps/sets, graphs, tries/n-ary trees, union-find

Tier 1+2 already cover most DSA courses — a demoable MVP exists before Tier 3 completes.
