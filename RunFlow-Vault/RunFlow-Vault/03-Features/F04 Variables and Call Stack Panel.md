---
tags: [runflow/feature]
status: draft
---
# F04 Variables and Call Stack Panel

## Content
- **Call stack**: frames (top first) with method name and current line; click a frame to view its locals.
- **Locals table**: Variable · Type · Value (refs show a chip that scrolls/highlights the object in the heap view) · Scope (frame).
- Values changed since previous step flash amber; new variables fade in.
- Out-of-scope variables are removed (no ghost vars).

## Rules
- Primitives and Strings inline; references link to heap objects ([[Heap Snapshot Model]]).
- Truncated values show `…` with tooltip; never render unbounded text ([[Execution Limits and Caps]]).
- All text rendered inertly ([[Frontend Safety]]).

## Acceptance
- [ ] Recursion shows distinct frames with separate locals
- [ ] Ref chips highlight the right heap object
- [ ] Changed-values highlight is correct on step back and forward
