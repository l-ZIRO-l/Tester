---
tags: [runflow/feature]
status: draft
---
# F08 Console and Input

## Console
- Bottom drawer (collapsible). Shows program stdout **up to current step** (so it replays in sync).
- Rendered as sanitized plain text in a `<pre>` — control characters/ANSI escapes stripped or visualized (`␛`) ([[Frontend Safety]]).
- Truncation banner if the output cap was hit ([[Execution Limits and Caps]]).
- stderr (e.g., exception stack trace) shown in a different color.

## Input (MVP = pre-filled, not interactive)
- "Custom Input" tab with a textarea (≤ 4 KB) sent as `stdin` with the run.
- Supports `Scanner(System.in)` and `BufferedReader`.
- If the program reads beyond the provided input → it fails like normal Java (`NoSuchElementException`); UI adds a hint: "Your program asked for more input than provided — add it in the Input tab."
- Truly interactive stdin → [[Post-MVP Parking Lot]] (needs session model + pause-aware timer).

## Acceptance
- [ ] Output appears incrementally as playback advances; back-step removes it
- [ ] Hostile output (escape codes, `<script>`, huge lines) is inert
- [ ] Scanner demo works with pre-filled input
