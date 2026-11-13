---
tags: [runflow/adr]
status: proposed
---
# ADR-002 — Line→CFG mapping strategy

**Status:** Proposed — decided after Spike S1 ([[Phase 1 - Spikes]]).

**Context:** STEP_LINE events give lines; CFG nodes/branches can share a line. Branch results can't be derived from lines alone ([[CFG Model and Line Mapping]]).

**Options:**
- A. Pure JDI + heuristics (no source change; ambiguous)
- B. Same-line condition probes `__rf.c(ID, cond)` + JDI state (exact branches; modifies source before compile)
- C. Bytecode-index stepping + line tables (no source change; complex)

**Leading hypothesis:** B.

**If B is chosen — rules:**
- Probes inserted on the same line (no line shifts), no new locals, whole condition wrapped (short-circuit preserved), only for `if/while/do/for/ternary-in-condition` conditions and `switch` selectors as needed.
- Probe class excluded from step requests and from user-visible stack frames.
- Behavior-equivalence tests mandatory ([[Test Strategy]]).
- Original source is what the user sees; only compiled source is instrumented.

**Consequences:** trade-off vs. blueprint's "don't alter source" preference — acceptable because the tracer is sandboxed and equivalence-tested.

**Decision record:** _(fill after S1: option chosen, evidence, failure modes)_
