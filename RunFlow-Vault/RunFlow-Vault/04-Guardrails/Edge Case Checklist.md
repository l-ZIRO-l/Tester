---
tags: [runflow/guardrail, runflow/test]
status: draft
---
# Edge Case Checklist

Tick when a test exists **and** passes ([[Test Strategy]]).

## Program behavior
- [ ] Infinite loop, multi-line → step cap, partial trace, banner
- [ ] Infinite loop, **single-line** (`while(true){x++;}`) → external time kill, UI banner
- [ ] Infinite recursion → `RECURSION_DEPTH_LIMIT` (and StackOverflowError handled)
- [ ] Circular references (doubly/circular lists, graph cycles, self-reference) → no infinite serialization
- [ ] Shared references (two vars → same object) drawn as one object
- [ ] Null fields/arrays elements, `null` inputs
- [ ] Huge arrays/collections → preview cap
- [ ] Huge strings → truncation
- [ ] 2D jagged arrays
- [ ] Generic classes (`Node<T>`), inner/static nested classes, records, enums, interfaces
- [ ] Multiple classes in one file; `Main` not first in file
- [ ] Static initializers and constructors (run before/outside `main`)
- [ ] Lambdas/streams/comparators passed to JDK methods (user code called from JDK)
- [ ] Early `return`, `break`/`continue`, labeled loops, `switch` fallthrough
- [ ] try/catch/finally; exception caught vs uncaught; custom exceptions
- [ ] Integer overflow, char arithmetic, long/double display
- [ ] Autoboxing (`Integer` vs `int`), `==` vs `equals` on boxed values
- [ ] `System.exit()` mid-run → graceful end note
- [ ] Program reads stdin beyond provided input
- [ ] Program spawns threads (only main traced; clear note in UI if other threads detected)
- [ ] Nondeterminism (`Random`, `nanoTime`, `HashMap` order) — trace is a recording; re-run may differ (UI note)
- [ ] Output with no trailing newline; unicode; very long lines

## Tracing mechanics
- [ ] StepRequest re-armed correctly after every event (no lost steps)
- [ ] JDK frames filtered, but user code called from JDK still traced
- [ ] `ObjectCollectedException` handled
- [ ] Tracer survives target crash/VM death mid-snapshot
- [ ] Payload within caps even for worst-case snapshots

## UI
- [ ] Panels collapse/resize without breaking Monaco
- [ ] Back-step across keyframe boundaries correct
- [ ] Render-as override persists during playback
- [ ] Large call tree collapse works
