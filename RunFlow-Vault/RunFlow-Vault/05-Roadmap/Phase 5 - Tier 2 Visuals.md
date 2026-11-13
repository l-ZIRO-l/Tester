---
tags: [runflow/phase]
status: draft
---
# Phase 5 — Tier 2 Visuals

**Topics:** linked lists, trees/BST, heaps/PQ.

## Tasks
- [ ] Structure-first shape detection implementation + tests ([[ADR-008 Structure-First Shape Detection]])
- [ ] Linked list renderer (singly/doubly/circular), pointer labels on nodes (`head/cur/prev/next/slow/fast`) (#6)
- [ ] Binary tree/BST renderer with d3-hierarchy layout, stable positions across steps (#7)
- [ ] Heap/PriorityQueue: array + tree dual view (#8)
- [ ] Re-pointing animations (edges flash on reassignment)
- [ ] Cycle handling in all renderers (circular list, tree with parent pointers)
- [ ] Ambiguity rules + override ([[F05 Heap Renderer Catalog]])
- [ ] Presets 7–8 + golden traces
- [ ] Performance check on 50-node structures (preview caps)

## Exit criteria
Demo: linked-list reversal, BST insert + inorder, heap sort / PQ — correct shapes and animations. Corpus T2 programs pass ([[DSA Test Corpus]]); no misdetections on the ambiguity test set (doubly list vs binary tree).

Next: [[Phase 6 - Tier 3 Visuals]]
