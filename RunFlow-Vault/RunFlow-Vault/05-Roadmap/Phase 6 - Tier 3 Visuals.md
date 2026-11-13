---
tags: [runflow/phase]
status: draft
---
# Phase 6 — Tier 3 Visuals

**Topics:** hashing, graphs, tries/n-ary trees, union-find.

## Tasks
- [ ] HashMap/HashSet/TreeMap renderer (bucket/chain view or key-value table) (#9)
- [ ] Graph renderer: adjacency list / matrix / node-with-neighbors detection; visited/current/frontier coloring; stable force layout (#10)
- [ ] BFS/DFS support: show queue/stack beside graph
- [ ] Trie & n-ary tree renderer (edge labels) (#11)
- [ ] Union-find helper view (optional) (#12)
- [ ] Preset 9 (BFS) + stdin preset 10 ([[F10 Preset Library]])
- [ ] Shape override covers graph ↔ matrix ↔ raw
- [ ] Caps respected for graphs (≤ 500 objects; collapse beyond)

## Exit criteria
Demo: BFS/DFS on small graph, HashMap two-sum, trie insert/search. Corpus T3 programs pass ([[DSA Test Corpus]]).

**If time is short:** ship MVP after Phase 5 + HashMap, and put graphs/tries into the first post-MVP release ([[Post-MVP Parking Lot]]) — but record the decision in [[Status Tracker]].

Next: [[Phase 7 - Sandbox and API]]
