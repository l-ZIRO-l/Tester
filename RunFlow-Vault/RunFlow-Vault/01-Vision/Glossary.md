---
tags: [runflow/vision]
status: active
---
# Glossary

| Term | Meaning |
|---|---|
| **CFG** | Control Flow Graph — nodes (start, action, decision, return…) and edges (Yes/No/Loop Back) built from the AST |
| **AST** | Abstract Syntax Tree from JavaParser |
| **JDI** | Java Debug Interface (`com.sun.jdi.*`) — lets one JVM inspect another (stack frames, locals, objects) |
| **Tracer** | Our Java program that compiles user code, launches the target JVM, and records steps via JDI |
| **Target JVM** | The JVM running the user's program under the tracer |
| **Trace** | The recorded JSON: CFG + ordered steps ([[Trace JSON Contract]]) |
| **Step** | One recorded moment (usually a line event) |
| **Keyframe** | A step carrying a full heap snapshot (every 25 steps); others carry diffs ([[Heap Snapshot Model]]) |
| **Logical heap** | Our normalized view of objects (ARRAY, LIST, MAP, OBJECT…) independent of JDK internals ([[ADR-006 Logical Heap Normalization]]) |
| **Shape** | How an object is drawn (array cells, linked list, tree, graph…) ([[F05 Heap Renderer Catalog]]) |
| **Probe** | Tiny inserted call (e.g., `__rf.c(id, cond)`) that reports a branch result — only if Spike S1 picks that option ([[ADR-002 Line Mapping Strategy]]) |
| **gVisor / runsc** | User-space kernel sandbox for containers |
| **Watchdog** | External process that kills a run that exceeds time/output limits |
| **Spike** | Time-boxed experiment to answer one risky question ([[Phase 1 - Spikes]]) |
| **Tier 1/2/3** | Order in which DSA visuals are delivered ([[Feature Index]]) |
