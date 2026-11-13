---
tags: [runflow/feature]
status: draft
---
# F06 Recursion Call Tree

## Behavior
- Built from `CALL` and `RETURN` steps (JDI MethodEntry/Exit; return values from MethodExit).
- Each node: `method(args)`; color by state: active (blue), completed (green with **return value badge**), pending (grey).
- Tree grows on call, nodes get badges on return; supports memoized/DP recursion (repeated subproblems visible).
- Cap: ≤ 64 depth, ≤ 300 nodes displayed; beyond → collapsed "…" node and banner ([[Execution Limits and Caps]]).
- Layout: d3-hierarchy; auto-scroll to active node; collapsible subtrees.

## Acceptance
- [ ] `fib(4)` shows the classic tree with returns
- [ ] Factorial, permutations/backtracking, merge sort recursion display correctly
- [ ] Infinite recursion stops cleanly at the depth cap with a banner
- [ ] Step-back correctly un-returns nodes

Data source: [[Trace JSON Contract]] (`CALL`/`RETURN` steps, `ret`). Test programs: [[DSA Test Corpus]].
