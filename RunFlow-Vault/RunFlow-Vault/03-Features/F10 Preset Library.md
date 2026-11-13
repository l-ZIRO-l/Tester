---
tags: [runflow/feature]
status: draft
---
# F10 Preset Library

Presets are static `Main.java` files bundled with the web app (no server call to list). Each has title, concept, one-line "what to watch".

| # | Preset | Teaches | Tier |
|---|---|---|---|
| 1 | Divide-by-N / state check | Yes/No diamonds, untaken `else` | – |
| 2 | Binary search | `low/mid/high` pointers | 1 |
| 3 | Bubble sort | nested loops + swaps | 1 |
| 4 | Fibonacci recursion `fib(4)` | call tree | 1 |
| 5 | Matrix / DP (climbing stairs or LCS small) | 2D grid | 1 |
| 6 | Balanced parentheses (stack) | stack push/pop | 1 |
| 7 | Reverse a linked list | pointer rewiring | 2 |
| 8 | BST insert + inorder | tree + recursion | 2 |
| 9 | BFS on small graph | queue + visited | 3 |
| 10 | Scanner input demo | stdin tab | – |

Rule: every preset must trace within **~1,000 steps** and be part of the golden-trace regression suite ([[Test Strategy]]). Sources shared with [[DSA Test Corpus]].
