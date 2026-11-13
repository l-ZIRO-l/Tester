---
tags: [runflow/feature]
status: draft
---
# F07 Pointer Labels

## Goal
Show `i`, `j`, `low`, `mid`, `high`, `left`, `right`, `slow`, `fast`, `head`, `cur`… directly under/over the cell/node they refer to.

## Derivation (not name-guessing)
1. **Static**: the AST finds simple-variable index expressions: `a[i]`, `a[mid]`, `a[j+1]` → `pointerHints: {method, array, indexVars}` ([[Trace JSON Contract]]).
2. **Runtime**: at each step, for each hinted `(array, var)` where `var` is an int local in range `[0,len)`, draw the label under that cell. Out-of-range values show a muted "out" marker beside the array (e.g., `high = -1`).
3. **Object pointers**: locals/fields referencing a node (`head`, `cur`, `prev`, `slow`, `fast`) are labeled on the node box (structural, from locals).
4. Multiple labels on one cell stack vertically.

## Acceptance
- [ ] Binary search: `low/mid/high` track correctly, including `high = -1`
- [ ] Two-pointer and sliding-window examples label both pointers
- [ ] Linked-list `prev/cur/next` labels move on each reassignment
- [ ] No labels for variables never used as indices (no noise)
