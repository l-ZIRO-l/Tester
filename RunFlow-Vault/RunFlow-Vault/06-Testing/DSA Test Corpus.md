---
tags: [runflow/test]
status: draft
---
# DSA Test Corpus

Store as `testcorpus/dsa/<id>/Main.java`. Write the first 10 in [[Phase 0 - Setup]] (marked ★). Each needs: golden trace, expected shape, expected node states.

## Tier 1
| ID | Program | Verify |
|---|---|---|
| ★T1-01 | Divide-by-N / even-odd check | Yes/No diamonds, untaken `else` stays grey |
| ★T1-02 | Binary search (see below) | `low/mid/high` labels, `while` loop-back, early `return` |
| ★T1-03 | Bubble sort | nested loops, swap highlights |
| T1-04 | Selection sort | min pointer |
| T1-05 | Insertion sort | shifting |
| ★T1-06 | Fibonacci recursion `fib(4)` | call tree + returns |
| T1-07 | Factorial / sum recursion | depth, unwinding |
| T1-08 | Merge sort (recursive) | call tree + subarrays |
| T1-09 | Two-pointer reverse / palindrome (String/char[]) | two pointer labels, string cells |
| T1-10 | 2D matrix traversal | grid pointers `r,c` |
| ★T1-11 | DP: climbing stairs / LCS (small) | table fill order |
| ★T1-12 | Balanced parentheses (`Stack<Character>`) | stack push/pop |
| T1-13 | Queue simulation (`ArrayDeque`) | enqueue/dequeue |
| T1-14 | Backtracking: permutations of 3 | call tree + state restore |
| T1-15 | `StringBuilder` building | char cell changes |
| T1-16 | Scanner input demo | stdin tab |

## Tier 2
| ID | Program | Verify |
|---|---|---|
| ★T2-01 | Reverse singly linked list (see below) | `prev/cur/next` labels, edge re-pointing |
| T2-02 | Detect cycle (Floyd) | `slow/fast`, cycle edge |
| T2-03 | Doubly linked list insert/delete | prev/next arrows; **not** a tree |
| T2-04 | Circular list traversal | no infinite loop in renderer |
| ★T2-05 | BST insert + inorder (recursive) (see below) | tree growth, call tree |
| T2-06 | BST search/delete | node removal |
| T2-07 | Heap sort (array-based) | array + tree view |
| T2-08 | `PriorityQueue` usage | PQ array order |

## Tier 3
| ID | Program | Verify |
|---|---|---|
| T3-01 | `HashMap` two-sum | buckets/entries |
| T3-02 | `HashSet` duplicates | set view |
| ★T3-03 | BFS (adj list `List<List<Integer>>`) | visited/frontier |
| T3-04 | DFS recursive/iterative | stack vs recursion |
| T3-05 | Adjacency matrix + Dijkstra (small) | `int[][]` as graph override |
| T3-06 | Trie insert/search | char edges |
| T3-07 | Union-find | parent array |
| T3-08 | `TreeMap` usage | sorted entries |

## Seed programs
**T1-02 Binary search**
```java
public class Main {
    static int binarySearch(int[] a, int target) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == target) return mid;
            else if (a[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] a = {2, 5, 8, 12, 16, 23, 38};
        System.out.println(binarySearch(a, 23));
    }
}
```
**T2-01 Reverse linked list**
```java
public class Main {
    static class Node { int val; Node next; Node(int v) { val = v; } }
    static Node reverse(Node head) {
        Node prev = null, cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head = reverse(head);
        System.out.println(head.val);
    }
}
```
**T2-05 BST insert**
```java
public class Main {
    static class TreeNode { int val; TreeNode left, right; TreeNode(int v) { val = v; } }
    static TreeNode insert(TreeNode root, int v) {
        if (root == null) return new TreeNode(v);
        if (v < root.val) root.left = insert(root.left, v);
        else root.right = insert(root.right, v);
        return root;
    }
    static void inorder(TreeNode n) {
        if (n == null) return;
        inorder(n.left);
        System.out.print(n.val + " ");
        inorder(n.right);
    }
    public static void main(String[] args) {
        TreeNode root = null;
        for (int v : new int[]{5, 3, 8, 1, 4}) root = insert(root, v);
        inorder(root);
    }
}
```
**T1-03 Bubble sort**
```java
public class Main {
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 1, 3};
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                }
            }
        }
        System.out.println(java.util.Arrays.toString(a));
    }
}
```

## CFG-focus set (used by Spike S1)
T1-01, T1-02, T1-03, T1-06, T1-12, T2-01 **plus** four dedicated programs: one-line `if`, `for` header variants, `&&`/`||` conditions, `switch` + `break/continue` + try/catch ([[CFG Model and Line Mapping]]).

Used by: [[Phase 2 - Tracer CLI]], [[Phase 4 - Tier 1 Visuals]], [[Phase 5 - Tier 2 Visuals]], [[Phase 6 - Tier 3 Visuals]], [[F10 Preset Library]].
