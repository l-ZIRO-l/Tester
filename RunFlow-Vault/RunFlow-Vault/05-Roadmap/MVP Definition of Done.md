---
tags: [runflow/phase]
status: active
---
# MVP Definition of Done

## Functional
- [ ] Paste any Java 17 DSA program (single file, `Main`) → Run Flow → full replay
- [ ] CFG with 4 node states; untaken branches visible; correct for corpus ([[F03 Execution Flow Graph]])
- [ ] Variables/stack panel, console, pointer labels ([[F04 Variables and Call Stack Panel]], [[F07 Pointer Labels]], [[F08 Console and Input]])
- [ ] Visuals for every row in the DSA tier map: arrays, 2D/DP, strings, stack/queue, linked lists, trees, heaps, hash maps, graphs, tries (+ raw fallback) ([[F05 Heap Renderer Catalog]])
- [ ] Recursion call tree ([[F06 Recursion Call Tree]])
- [ ] Playback: play/pause/step/scrub/speed/keys; exact step-back ([[F02 Playback Controller]])
- [ ] Compile errors, runtime exceptions, and limit banners ([[F09 Errors and Exceptions UX]])
- [ ] 8–10 presets ([[F10 Preset Library]])

## Quality
- [ ] All [[DSA Test Corpus]] programs pass (shape + CFG correctness)
- [ ] Behavior-equivalence: traced output == plain Java output for corpus
- [ ] Performance targets met ([[Test Strategy]])
- [ ] Accessibility basics (contrast, keyboard, non-color state cues)

## Safety
- [ ] [[Public Launch Gate]] fully ticked
- [ ] [[Adversarial Test Corpus]] all green on the real stack
- [ ] [[Risk Register]] reviewed; no open High risks without mitigation

## Process
- [ ] README with setup, architecture, and run instructions
- [ ] Vault updated: [[Status Tracker]], ADRs accepted, Spike reports filed
- [ ] Retrospective note written; post-MVP vault folders planned ([[Post-MVP Parking Lot]])
