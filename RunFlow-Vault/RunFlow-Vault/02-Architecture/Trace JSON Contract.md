---
tags: [runflow/architecture, runflow/contract]
status: draft
---
# Trace JSON Contract (v1 draft)

> [!note] Source of truth will be `schemas/trace.schema.json` ([[Repo Structure]]). This note explains intent. Finalize after Spike S1/S2.

## Top level
```jsonc
{
  "schemaVersion": 1,
  "status": "SUCCESS",              // see statuses in [[Execution Pipeline]]
  "message": null,                  // human-readable for non-SUCCESS
  "meta": { "compileMs": 410, "runMs": 820, "totalSteps": 31,
            "truncated": false, "keyframeEvery": 25 },
  "diagnostics": [],                // compile errors: {line, col, message, severity}
  "graph": { "methods": [], "nodes": [], "edges": [] },
  "pointerHints": [ { "method": "binarySearch", "array": "a",
                      "indexVars": ["low", "mid", "high"] } ],
  "steps": []
}
```

## Graph
```jsonc
"nodes": [
  { "id": "bs_while", "method": "binarySearch", "type": "DECISION",
    "label": "low <= high?", "lineStart": 4, "lineEnd": 4 }
],
"edges": [
  { "id": "e3", "from": "bs_while", "to": "bs_if1", "label": "Yes" }
]
```
Node types: `START, END, ACTION, DECISION, CALL, RETURN, THROW`. Edge labels: `Yes, No, Loop Back, Break, Continue, Exception, Case`. No x/y: layout is client-side. See [[CFG Model and Line Mapping]].

## Step
```jsonc
{
  "i": 5, "kind": "LINE",           // LINE | CALL | RETURN | EXCEPTION
  "line": 5, "node": "bs_if1", "viaEdge": "e4",
  "ret": null,                      // value on RETURN steps
  "stack": [
    { "frame": "f2", "method": "binarySearch", "line": 5,
      "locals": { "low": {"t":"int","v":0}, "a": {"t":"ref","id":"h1"} } }
  ],
  "heap":     { "h1": { "kind":"ARRAY", "cls":"int[]", "len":3,
                        "el":[{"t":"int","v":2},{"t":"int","v":5},{"t":"int","v":8}] } },
  "heapDiff": null,                 // keyframe steps have "heap"; others have "heapDiff": {"set":{id:obj},"del":[ids]}
  "stdoutAppend": ""
}
```
Exactly one of `heap` (keyframe, every 25 steps and step 0) or `heapDiff`.

## Values
`{"t":"int|long|double|float|short|byte|boolean|char","v":…}` · `{"t":"str","v":"abc","trunc":false}` (Strings inline, not heap objects) · `{"t":"null"}` · `{"t":"ref","id":"h12"}` · `{"t":"more","n":950}` for truncated previews.

## Rules
- All strings are untrusted; client renders as text only ([[Frontend Safety]]).
- Hard caps on sizes: [[Execution Limits and Caps]].
- Additive changes only within a `schemaVersion`; breaking → bump.
- Heap object kinds and fields: [[Heap Snapshot Model]].
