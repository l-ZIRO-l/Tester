---
tags: [runflow/feature]
status: draft
---
# F01 Editor and Run

## Behavior
- Monaco editor, `java` language, `vs-dark`, `automaticLayout: true`.
- Starts with a default sample (e.g., binary search) so first-time users can press Run immediately.
- **Run Flow** button: disabled while a run is in flight; shows spinner and elapsed time; cancel not required in MVP (server enforces limits).
- Editing code after a run **invalidates** the trace (banner: "Code changed — run again") so line highlights never point at stale code.
- Autosave draft to `localStorage` (try/catch; works without it).
- Client-side pre-checks: size ≤ 20 KB, non-empty, contains `class Main`.

## Acceptance
- [ ] Run with sample returns and renders in < target time ([[Test Strategy]])
- [ ] Compile error shows squiggles at correct line/col ([[F09 Errors and Exceptions UX]])
- [ ] Stale-trace banner appears on edit
- [ ] Oversized source blocked client-side with a clear message
- [ ] Works with localStorage disabled

Guardrails: [[API Abuse Protection]], [[Frontend Safety]]. Layout: [[F11 Workspace Layout]].
