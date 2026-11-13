---
tags: [runflow/feature]
status: draft
---
# F05 Heap Renderer Catalog

Pipeline: logical heap object → **shape detector** (structure first, names second: [[ADR-008 Structure-First Shape Detection]]) → renderer. User can override via "Render as" dropdown. Anything unrecognized → raw object graph.

Common behavior: changed cells/fields glow amber; new objects pop in; removed objects fade; large previews capped with "+N more"; cycle-safe.

| # | Shape | Detected from | Visual | Highlights | Tier |
|---|---|---|---|---|---|
| 1 | **Array 1D** | `ARRAY`, `LIST` (ArrayList) | Indexed horizontal cells | changed cell, pointer labels ([[F07 Pointer Labels]]), compare/swap pair | 1 |
| 2 | **Array 2D / DP table** | array of arrays | Grid with row/col indices | changed cell; current cell; row/col headers | 1 |
| 3 | **String / StringBuilder** | `str`, `STRINGBUILDER` | Char cells with indices | changed char, two-pointer labels | 1 |
| 4 | **Stack** | `STACK`, or ArrayDeque used LIFO (push/pop pattern) | Vertical stack, top marked | push/pop animation | 1 |
| 5 | **Queue / Deque** | `DEQUE`, `LinkedList` as queue | Horizontal queue, head/tail labels | enqueue/dequeue | 1 |
| 6 | **Singly/Doubly/Circular linked list** | `OBJECT` with 1 self-ref (`next`) or 2 (`next/prev`, back-edge) | `[data \| next]` boxes + arrows; head/tail/cur labels | re-pointed edges flash | 2 |
| 7 | **Binary tree / BST** | `OBJECT` with 2 self-refs (`left/right`) | Auto-laid-out tree (d3-hierarchy) | visited node, inserted node, current node | 2 |
| 8 | **Heap / PriorityQueue** | `PQ`, or array used as heap | **Array + tree** side by side | sift-up/down changed indices | 2 |
| 9 | **Hash map / set** | `MAP`, `SET` | Bucket table (index → chain) or key/value table | inserted/removed key | 3 |
| 10 | **Graph** | `List<List<Integer>>`, `Map<K,List<K>>`, `int[][]` adjacency, node objects with neighbor list | Node-edge diagram + visited set + frontier (queue/stack) | visited/current/frontier colors | 3 |
| 11 | **Trie / N-ary tree** | `OBJECT` with `Node[]`/`Map<Char,Node>`/`List<Node>` children | Tree with edge labels (chars) | path highlighted | 3 |
| 12 | **Union-Find** | parent/rank arrays | Array view + optional forest | union/find path | 3 (optional) |
| 13 | **Raw object graph** | anything else | Boxes with fields + arrows | changed fields | 1 (fallback) |

## Ambiguity rules
- Two self-refs: if names are `prev/next` **or** back-edge exists (`child.prev == parent`) → doubly linked list; else binary tree.
- `ArrayDeque` ambiguity (stack vs queue): infer from operations if available; else show as deque with head/tail and let user override.
- 2D `char[][]`/`int[][]` representing grids: grid view; adjacency-matrix graph view is an **override**, not auto.

## Acceptance (per shape)
- [ ] Correct auto-detection on corpus programs ([[DSA Test Corpus]])
- [ ] Correct under mutation (pointer re-assignments, rotations, swaps)
- [ ] Cyclic structures render without infinite loops
- [ ] Respects preview caps ([[Execution Limits and Caps]])
- [ ] Override works; raw fallback never crashes
