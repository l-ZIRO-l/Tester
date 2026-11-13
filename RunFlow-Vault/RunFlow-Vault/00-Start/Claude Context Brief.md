---
tags: [runflow/context]
status: active
---
# Claude Context Brief (paste into new AI chats)

## What we're building
**RunFlow Visualizer**: a web IDE for **Java 17 DSA programs** (no frameworks). The user pastes code and clicks **Run Flow**. The screen shows, in sync and replayable:
1. Monaco editor with the current line highlighted
2. **Control-flow graph** (decision diamonds, action boxes) with node states: Executed / Current / Not Executed / Crashed
3. **Variables + call stack** table and **data-structure pictures** (arrays, 2D grids/DP, strings, stack/queue, linked lists, trees, heaps/PQ, hash maps, graphs)
4. Console output, and a playback bar (play, pause, step back/forward, speed, scrubber)

Differentiator: macro flowchart + micro memory view on **arbitrary user code** (Python Tutor lacks the flowchart; VisuAlgo can't run your code).

## Scope = DSA only
Arrays, strings, sorting/searching, recursion/backtracking, DP, stacks/queues, linked lists, trees/BST, heaps, hashing, graphs, tries. **Not** Spring/Hibernate/etc. See [[MVP Scope]].

## Architecture in 6 lines
1. Browser (React+TS) sends `POST /api/v1/visualize {source, stdin}` → API (Node/Fastify).
2. API starts **one gVisor container per run** (cold start in MVP) and pipes source in via stdin.
3. **Inside the sandbox** a Java tracer jar: parses (JavaParser) → builds CFG → compiles in memory → launches target JVM → traces with JDI → writes JSON trace to container stdout. See [[ADR-003 All Untrusted Processing Inside Sandbox]].
4. User's own stdout is captured by the tracer and kept **separate** from the trace channel ([[ADR-004 Trace Channel Separate From User Stdout]]).
5. API validates size and schema, returns the trace.
6. Browser replays the recorded trace (full heap keyframe every 25 steps, diffs between).
Details: [[System Topology]], [[Execution Pipeline]], [[Trace JSON Contract]].

## Key decisions (see [[ADR Index]])
- Java 17 only for MVP. Python/JS/C++/Verilog are post-MVP ([[Post-MVP Parking Lot]]).
- Line→CFG-node mapping strategy is **decided by Spike S1** ([[ADR-002 Line Mapping Strategy]]).
- Heap is normalized to a logical model; shapes are detected **by structure first, names second** ([[ADR-006 Logical Heap Normalization]], [[ADR-008 Structure-First Shape Detection]]).

## Non-negotiable guardrails (see [[Guardrails Index]])
- Treat all user code as hostile. No public exposure before [[Public Launch Gate]] passes.
- Tracer and user code must **not** share a uid (Spike S3); the watchdog lives **outside** the sandbox.
- Never call user `toString()`/`hashCode()` through JDI; read fields only.
- All user-derived strings are rendered as inert text. No `dangerouslySetInnerHTML`.
- Hard caps on steps, time, output, payload ([[Execution Limits and Caps]]).

## Rules for AI assistants working on this project
1. Read [[Status Tracker]] first; update it after finishing work.
2. Don't expand scope beyond [[MVP Scope]]; put ideas in [[Post-MVP Parking Lot]].
3. Any design choice with trade-offs → write an ADR from [[Template ADR]].
4. New feature → add to [[Feature Index]] with acceptance criteria.
5. Every new limit/guardrail → record in [[Execution Limits and Caps]] or [[Risk Register]].
6. Prefer the simplest thing that satisfies the current phase's exit criteria ([[Roadmap Overview]]).
7. Log meaningful sessions in [[Daily Log]].
