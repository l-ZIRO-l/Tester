---
tags: [runflow/phase]
status: draft
---
# Phase 2 — Tracer CLI

**Goal:** `java -jar tracer.jar < request.json > trace.json` works for all corpus programs. No web, no Docker yet (but design for it: stdin in, stdout out — [[ADR-003 All Untrusted Processing Inside Sandbox]], [[ADR-004 Trace Channel Separate From User Stdout]]).

## Tasks
**Static**
- [ ] JavaParser (JAVA_17) → AST; error mapping to diagnostics
- [ ] CFG builder for all supported constructs ([[MVP Scope]], [[CFG Model and Line Mapping]]); opaque nodes for unsupported
- [ ] Pointer hint extraction ([[F07 Pointer Labels]])

**Compile**
- [ ] In-memory `JavaCompiler` (`-g -proc:none -implicit:none`), diagnostics → `COMPILE_ERROR`
- [ ] Multi-class single file; non-first `Main`

**Trace**
- [ ] Launch target JVM with flags ([[Sandbox Hardening Spec]]); capture target stdout/stderr
- [ ] Step requests (re-armed), method entry/exit, exception requests ([[Execution Pipeline]])
- [ ] Stack/locals capture with frame ids; depth cap
- [ ] Heap capture + normalization for all kinds ([[Heap Snapshot Model]])
- [ ] Keyframes + diffs; stdout deltas
- [ ] Step cap, output cap, depth cap, partial traces; statuses ([[Execution Limits and Caps]])
- [ ] Robustness: VM death mid-snapshot, `ObjectCollectedException`, target `System.exit`

**Quality**
- [ ] JSON validates against `schemas/trace.schema.json`
- [ ] Golden traces for corpus reviewed by hand ([[Test Strategy]])
- [ ] Behavior-equivalence test: traced program output == plain `java` output
- [ ] Unit tests per heap extractor; fuzz-ish tests with random small programs (optional)

## Exit criteria
- All 10 corpus programs → valid traces with correct CFG attribution (≥ 95% from S1) and heap shapes
- Limits produce correct statuses on adversarial programs run locally (A01–A06, A15, A26)
- Trace sizes within caps

## Guardrails to apply now
No `invokeMethod`; trace validation; no reading beyond caps ([[Guardrails Index]]).

Next: [[Phase 3 - UI Shell]] (can run in parallel once contract is frozen)
