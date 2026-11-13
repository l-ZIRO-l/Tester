---
tags: [runflow/architecture]
status: active
---
# Execution Pipeline

## Phase 1 — Static analysis (inside sandbox)
1. Read request JSON from stdin: `{source, stdin}`.
2. Pre-checks (UX only, **not** security): size, `class Main` + `main` present, friendly warnings for banned APIs (`Runtime.exec`, `ProcessBuilder`, `java.net`, file I/O, reflection, `System.exit`).
3. JavaParser (language level JAVA_17) → AST. Parse failure → `COMPILE_ERROR` with line/col.
4. Build per-method CFG ([[CFG Model and Line Mapping]]), line ranges per node, pointer hints from `arr[i]` index usage ([[F07 Pointer Labels]]).
5. Layout is done **client-side** (Dagre) so the tracer stays small; tracer emits no x/y in MVP. *(Changed from blueprint.)*

## Phase 2 — Compile and trace (inside sandbox)
1. `javax.tools.JavaCompiler` with options `-g -proc:none -implicit:none` and an in-memory file manager. Errors → `COMPILE_ERROR` with diagnostics (line, col, message).
2. Write `.class` files to a tracer-owned tmpfs dir (target JVM must load them; `noexec` does not affect class loading).
3. Launch target JVM (separate uid per [[Sandbox Hardening Spec]]) with flags: `-Xmx`, `-Xss`, `-XX:+UseSerialGC`, `-XX:TieredStopAtLevel=1`, `-XX:-UsePerfData`, JDWP via launching connector.
4. Register requests:
   - `StepRequest` STEP_LINE/STEP_INTO on the main thread, class-exclusion filters for `java.*`, `javax.*`, `jdk.*`, `sun.*`, `com.sun.*` and our probe class. **Re-arm** after each step event.
   - `MethodEntry/ExitRequest` (user classes only) for call tree and return values ([[F06 Recursion Call Tree]]).
   - `ExceptionRequest` (uncaught + notable caught) for crash nodes ([[F09 Errors and Exceptions UX]]).
   - Optional breakpoint on probe method if Option B ([[ADR-002 Line Mapping Strategy]]).
5. Each event: capture stack (≤64 frames), locals, reachable heap (bounded), `disableCollection()` on tracked objects, stdout delta.
6. Normalize heap ([[Heap Snapshot Model]]); emit keyframe every 25 steps, otherwise diff.
7. Stop on: normal exit, uncaught exception, step cap, depth cap, output cap. External watchdog handles time/memory.
8. Write final JSON trace to container stdout (user stdout is captured separately, [[ADR-004 Trace Channel Separate From User Stdout]]).

## Phase 3 — Client playback
Validate trace (zod) → init React Flow with all nodes `NOT_EXECUTED` → on step N highlight line, set node `CURRENT`, mark visited `EXECUTED`, update panels, console, call tree. Details: [[F02 Playback Controller]].

## Failure statuses
`SUCCESS · COMPILE_ERROR · RUNTIME_EXCEPTION · STEP_LIMIT_EXCEEDED · TIME_LIMIT_EXCEEDED · OUTPUT_LIMIT_EXCEEDED · MEMORY_LIMIT_EXCEEDED · RECURSION_DEPTH_LIMIT · REJECTED · SANDBOX_ERROR · INTERNAL_ERROR` — partial traces are returned where possible.
