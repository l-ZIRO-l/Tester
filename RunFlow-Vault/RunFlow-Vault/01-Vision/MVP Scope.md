---
tags: [runflow/vision, runflow/scope]
status: active
---
# MVP Scope

> [!important] Rule
> If it's not listed under **In**, it waits in [[Post-MVP Parking Lot]].

## In (Java 17, DSA only)
**Platform**
- Single-file `Main.java` (class `Main` with `public static void main`), plus extra top-level/nested helper classes, generics, records, enums, interfaces in the same file.
- Compile errors with inline squiggles; runtime exceptions with crash node.
- Pre-filled stdin box for `Scanner`/`BufferedReader` (not interactive).

**Visuals (tiers; see [[Feature Index]])**
- Tier 1: 1D/2D arrays, strings/`StringBuilder`, sorting/searching, recursion call tree, DP tables, stack/queue (`Stack`, `ArrayDeque`, `Queue`/`Deque`)
- Tier 2: linked lists (singly/doubly/circular), binary trees/BST, heaps/`PriorityQueue`
- Tier 3: `HashMap`/`HashSet`/`TreeMap`, graphs (adj list/matrix/neighbor nodes), tries/n-ary trees, union-find (via arrays)
- Fallback: raw object graph for anything unrecognized

**UX**
- 4-pane resizable workspace, dark theme ([[F11 Workspace Layout]])
- Playback: play/pause/step/scrub/speed + keyboard shortcuts ([[F02 Playback Controller]])
- Execution Flow Graph with 4 node states and per-method scope switcher ([[F03 Execution Flow Graph]])
- Variables & call stack ([[F04 Variables and Call Stack Panel]]), pointer labels ([[F07 Pointer Labels]])
- 8–10 presets ([[F10 Preset Library]])
- Draft code autosaved in browser localStorage

**Backend & safety**
- One gVisor container per run, cold start, hard limits ([[Sandbox Hardening Spec]])
- Rate limiting, concurrency/queue caps, output caps ([[API Abuse Protection]], [[Execution Limits and Caps]])
- [[Public Launch Gate]] passed before any public URL

## Out (until after MVP)
Other languages · accounts/login · sharing/saved traces · interactive (live) stdin · breakpoints · multithreaded tracing · Redis warm pool · AppCDS/latency tuning · collaborative features · mobile layout · light theme · frameworks (Spring etc.) · Verilog/HDL.

## Supported Java constructs (graceful degradation)
Supported in CFG: `if/else if/else`, `while`, `do-while`, `for`, enhanced `for`, `switch` (classic + arrow), `break/continue` (incl. labeled), `return`, `throw`, `try/catch/finally`, method calls. Lambdas/streams/ternaries are shown as **one opaque action node** (no inner flow). Unknown constructs never fail the graph: they become an `ACTION` node labeled with the source text.

Done = [[MVP Definition of Done]].
