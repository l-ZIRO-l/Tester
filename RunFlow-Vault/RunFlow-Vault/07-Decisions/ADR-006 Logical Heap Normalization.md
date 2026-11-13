---
tags: [runflow/adr]
status: accepted
---
# ADR-006 — Logical heap normalization in the tracer

**Context:** UI must render any DSA program including JDK collections, whose internals (`elementData`, `table`, `Node`…) vary and are not meant for display.
**Decision:** The tracer reads JDK internals (field reads only) and emits **logical kinds** (`ARRAY, LIST, STACK, DEQUE, PQ, MAP, SET, OBJECT, …`). The UI never sees JDK internals. Unknown types → `UNKNOWN`/raw.
**Consequences:** JDK-version coupling lives in one place (tracer, pinned Java 17, tested in S2); UI stays simple; new JDK types are added by extending extractors. Treeified HashMap bins handled as nodes.
Related: [[Heap Snapshot Model]], [[ADR-001 Java 17 Only]], [[ADR-008 Structure-First Shape Detection]].
