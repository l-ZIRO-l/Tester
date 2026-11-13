---
tags: [runflow/architecture]
status: draft
---
# Heap Snapshot Model

Goal: the UI never needs to know JDK internals. The tracer converts raw objects into **logical kinds** ([[ADR-006 Logical Heap Normalization]]).

## Kinds
| kind | Source types | Fields |
|---|---|---|
| `ARRAY` | `T[]`, primitives, nested arrays (2D = array of refs) | `cls, len, el[]` |
| `STRINGBUILDER` | `StringBuilder/Buffer` | `text` (truncated) |
| `LIST` | `ArrayList, LinkedList, List.of, Arrays.asList` | `cls, size, el[]` |
| `STACK` | `java.util.Stack` | `el[]` bottom→top |
| `DEQUE` | `ArrayDeque, LinkedList used as Deque` | `el[]` head→tail |
| `PQ` | `PriorityQueue` | `el[]` in **array order** (heap layout) |
| `MAP` | `HashMap, LinkedHashMap, TreeMap` | `entries[[k,v]]`, optional `buckets` |
| `SET` | `HashSet, LinkedHashSet, TreeSet` | `el[]` |
| `OBJECT` | user classes | `cls, f{name: value}`, `shapeHint` |
| `UNKNOWN` | other JDK/user types | `cls`, minimal fields |

`shapeHint` on `OBJECT`: `LIST_NODE | DLIST_NODE | BINARY_TREE_NODE | NARY_TREE_NODE | GRAPH_NODE | GENERIC` — computed structurally ([[ADR-008 Structure-First Shape Detection]]).

## How the tracer reads JDK collections (JDI = field reads only)
| Type | Fields read |
|---|---|
| `ArrayList` | `elementData`, `size` |
| `LinkedList` | `first`, `Node.next`, `size` |
| `ArrayDeque` | `elements`, `head`, `tail` |
| `PriorityQueue` | `queue`, `size` |
| `HashMap` | `table`, `Node.key/value/next`, (treeified bins: read as nodes, note in UI) |
| `TreeMap` | `root`, `Entry.left/right/key/value` |
| `Stack/Vector` | `elementData`, `elementCount` |

> [!warning] Never call `toString()/hashCode()/equals()` via JDI `invokeMethod` — it runs user code outside step accounting. Pin **Java 17**; internals change across versions (Spike S2 verifies each).

## Identity and GC
- Object ids = `uniqueID()` mapped to compact `h<n>` ids.
- Call `disableCollection()` for tracked objects; **re-enable/release** objects no longer reachable to avoid memory blow-up. Handle `ObjectCollectedException` gracefully.

## Bounds (see [[Execution Limits and Caps]])
Preview ≤ 50 elements per array/collection (`{"t":"more"}` marker), 2D ≤ 20×20 visible, ≤ 500 objects per snapshot, strings ≤ 200 chars, cycle-safe via visited set per snapshot.

## Diffs
Keyframe every 25 steps; between them `heapDiff {set, del}`. Client reconstructs any step N by loading nearest keyframe ≤ N and applying diffs ([[F02 Playback Controller]]).
Changed cells since previous step are computed **client-side** (for amber highlight).
