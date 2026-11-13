---
tags: [runflow/adr]
status: accepted
---
# ADR-008 — Structure-first shape detection

**Context:** Detecting lists/trees from field *names* (`next`, `left`) fails for names like `nxt`, `leftChild`, `children`, or for doubly lists vs binary trees.
**Decision:** Detect shapes from **structure**: field types equal to the owning class (self-references) and their count/pattern; collections of self type; arrays of self type. Use **names only to label** edges/ties. Add rules: back-edge (`child.prev == parent`) ⇒ doubly linked list; `List<Node>/Node[]/Map<?,Node>` children ⇒ n-ary tree/trie; neighbor collections ⇒ graph node.
**Safety valve:** user-visible "Render as" override (auto/list/tree/graph/raw).
**Consequences:** fewer misdetections; ambiguity test set required ([[Phase 5 - Tier 2 Visuals]]).
Related: [[F05 Heap Renderer Catalog]], [[Risk Register]] R-09.
