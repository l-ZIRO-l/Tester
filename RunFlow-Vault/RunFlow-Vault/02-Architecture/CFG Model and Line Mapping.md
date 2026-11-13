---
tags: [runflow/architecture, runflow/risk-high]
status: draft
---
# CFG Model and Line Mapping

> [!danger] The riskiest part of the project
> JDI `STEP_LINE` events give **lines**, but one line can hold several CFG nodes (`for (init; cond; update)`, `if (x) return;`, ternaries, short-circuit `&&`). Branch results (Yes/No) cannot be derived reliably from lines alone. This is resolved by **Spike S1** → [[ADR-002 Line Mapping Strategy]].

## CFG construction
- One CFG **per method**; UI shows the CFG of the current top frame (scope dropdown, as in the UI mockup).
- Node types: `START, END, ACTION, DECISION, CALL, RETURN, THROW`.
- `for` loop → init (ACTION), cond (DECISION), body…, update (ACTION), edge `Loop Back` to cond.
- `while/do-while` → DECISION with `Loop Back`; `break/continue` edges explicit; labeled break/continue supported.
- `switch` → DECISION with `Case` edges (+ fallthrough edges for classic switch).
- `try/catch/finally` → try body, `Exception` edges to catch nodes, finally node.
- Lambdas, streams, ternaries → single ACTION node (opaque).
- Unknown construct → ACTION with source text label (graph never fails).

## Node state semantics (with recursion)
- `EXECUTED`: node visited in **any** frame so far.
- `CURRENT`: node at the **top frame** for this step.
- `CRASHED`: node where an uncaught exception originated.
- `NOT_EXECUTED`: default; reveals untaken branches and dead code.
- Edges: highlight the edge actually traversed (`viaEdge`).

## Mapping options (decide in S1)
| Option | How | Pros | Cons |
|---|---|---|---|
| **A. Pure JDI lines + heuristics** | Map line → node; infer branch from next line | No source change | Ambiguous for single-line constructs; fragile |
| **B. Condition probes + JDI state** | Rewrite conditions inline: `if (cond)` → `if (__rf.c(ID, cond))` on the **same line** (no line shifts, no new locals, short-circuit preserved). Breakpoint on probe reads `(ID, result)`; JDI captures state | Exact branch results; simple mapping | Source is modified before compile; need probe-class exclusion; ternary/lambda caveats |
| **C. Bytecode-index stepping (STEP_MIN) + line table** | Map bytecode offsets to AST | No source change | Complex; compiler-dependent |

**Leading hypothesis: B** (verify). Original blueprint said "without altering user source" — that is a *preference*, not a requirement; trade-off recorded in the ADR.

## Spike S1 acceptance criteria
- ≥ 95% correct node + branch attribution on the 10 CFG test programs ([[DSA Test Corpus]]) incl.: one-line `if`, `for` header, `while` with `&&`/`||`, `else if` chains, `switch`, nested loops, recursion, early `return`, `break/continue`, try/catch.
- Zero change to program behavior/output for all corpus programs (A/B compare against plain `java`).
- Documented failure modes for unsupported constructs.

## Other known gotchas
- A one-line loop (`while(true){x++;}`) emits no new line events → only the **external watchdog** stops it ([[Execution Limits and Caps]]).
- StepRequests are completed by an event; **re-arm** each time.
- Methods invoked from JDK callbacks (comparators, lambdas passed to `sort`) must still be traced if they are user classes — verify in S1.
- Static initializers and constructors run before/outside `main`; show them as CALL frames.
