---
tags: [runflow/vision]
status: active
---
# Target Users

- **CS students** learning DSA, recursion, pointers/references (primary).
- **Bootcamp / self-taught learners** debugging branching logic and runtime exceptions.
- **Educators / tutors** demonstrating step-by-step execution (sharing links is **post-MVP**, see [[Post-MVP Parking Lot]]).

## Implications for design
- Programs are **small** (tens of lines, inputs of ≤ ~20 elements). Optimize for clarity, not scale → informs [[Execution Limits and Caps]].
- Typical style: one `Main` class, static methods, helper classes (`Node`, `TreeNode`), `ArrayList/HashMap/ArrayDeque/PriorityQueue`.
- Users make mistakes (compile errors, infinite loops, NPEs) → errors must be *teachable* ([[F09 Errors and Exceptions UX]]).
- Anonymous visitors will paste anything, including hostile code → [[Threat Model]].
