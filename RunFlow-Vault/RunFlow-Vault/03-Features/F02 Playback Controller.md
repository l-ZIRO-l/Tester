---
tags: [runflow/feature]
status: draft
---
# F02 Playback Controller

## Controls
`⏮ first` · `◀ step back` · `▶/⏸ play/pause` · `▶ step forward` · `⏭ last` · speed 0.5x/1x/2x/3x · scrubber · "Step N / total" · jump-to-step input.
Keyboard: `←/→`, `Space`, `Home/End`.

## Behavior
- One cursor `n` drives **everything**: editor line, graph node, panels, call tree, console text ([[Frontend Architecture]]).
- Back-step = `n-1`; no re-execution (trace is recorded).
- Auto-pause at end; if trace is truncated, show the banner at the last step ([[F09 Errors and Exceptions UX]]).
- Smooth at 5,000 steps: snapshot reconstruction uses keyframe + diffs and memoization.

## Acceptance
- [ ] Scrubbing across 5,000 steps stays interactive (no long frames > 50 ms typical)
- [ ] All panels remain in sync at random-access jumps
- [ ] Speed change applies immediately
- [ ] Keyboard shortcuts work when editor is not focused

Data: [[Heap Snapshot Model]], [[Trace JSON Contract]].
