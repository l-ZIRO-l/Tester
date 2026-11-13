---
tags: [runflow/architecture]
status: draft
---
# Frontend Architecture

## Modules
```
src/
  state/        # zustand: source, trace, cursor, playback, panel layout, ui prefs
  trace/        # zod schema, loader, reconstruct(stepN) (keyframe+diffs), selectors
  components/   # EditorPane, FlowGraph, VariablesPanel, HeapPanel, ConsolePanel, TopBar, Timeline
  renderers/    # ArrayView, GridView, StringView, StackView, QueueView, ListView, TreeView, HeapTreeView, MapView, GraphView, RawGraphView
  lib/          # sanitize, layout helpers, shape detection overrides
```

## Data flow
`Run Flow` → `POST` → raw JSON → **zod validate** → store `trace` → cursor `n` (0..totalSteps-1).
For each render: `snapshot(n) = applyDiffs(nearestKeyframe(n), n)` (memoize last K snapshots). Derive: current line, active node, executed set (precomputed prefix sets or incremental), stack, locals, changed-since-previous, call tree, console text up to `n`.

## Playback engine
Timer-driven (speed 0.5x–3x), pause on end; scrubber for random access; keyboard: `←/→` step, `Space` play/pause, `Home/End`. Back-stepping is just `n-1` (no re-execution). See [[F02 Playback Controller]].

## Rendering rules
- Text from the trace is rendered via React text nodes / SVG `<text>` only → [[Frontend Safety]].
- Large structures use the preview markers (`more`) and never render > caps.
- Renderer chosen by shape detection with a **user override dropdown** ("Render as: auto/list/tree/graph/raw") → [[F05 Heap Renderer Catalog]], [[ADR-008 Structure-First Shape Detection]].
- Graph layout (Dagre) runs in a worker if it measurably blocks the UI.

## Error handling
Schema-invalid trace → friendly "Something went wrong on our side" + report id; never crash the whole app (error boundaries per panel).

## Storage
`localStorage` (try/catch): last source, panel sizes, speed. No traces persisted.
