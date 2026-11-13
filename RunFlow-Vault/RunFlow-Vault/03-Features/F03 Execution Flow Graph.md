---
tags: [runflow/feature]
status: draft
---
# F03 Execution Flow Graph

## Visual language
| State | Style |
|---|---|
| Not Executed | Slate grey `#4B5563` |
| Executed | Emerald `#10B981` + check |
| Current | Blue `#3B82F6` + pulsing glow |
| Crashed | Crimson `#EF4444` + alert badge |

Shapes: Start/End = pills · Decision = diamond · Action = rounded box · Call/Return/Throw = distinct accent. Edges animated when just traversed; labels `Yes/No/Loop Back/Break/Continue/Exception/Case`.
Not color-only: also use icons/patterns for accessibility (color-blind safe).

## Behavior
- **Scope switcher** (dropdown, like the mockup's "Scope"): defaults to method of the top frame; auto-follows calls/returns, with a "pin scope" toggle.
- Pan/zoom, fit-to-view button, auto-center on current node (toggle).
- Click node → highlight its source lines in the editor.
- Dagre layout computed client-side; cached per method.
- Legend visible (Executed / Current / Not Executed / Crashed).

## Acceptance
- [ ] Untaken branches remain grey after run completes
- [ ] Loop-back edges animate on each iteration
- [ ] Recursive calls: Current follows the top frame; Executed accumulates
- [ ] Unsupported construct still renders as an action node
- [ ] Graph for a 100-line program renders without overlap

Design/risks: [[CFG Model and Line Mapping]], [[ADR-002 Line Mapping Strategy]].
