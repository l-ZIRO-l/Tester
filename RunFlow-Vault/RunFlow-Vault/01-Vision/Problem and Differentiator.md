---
tags: [runflow/vision]
status: active
---
# Problem and Differentiator

## Problem
Learners face fragmented tools:
| Tool type | Example | Gap |
|---|---|---|
| Online compilers | Programiz, OnlineGDB | Only final output; no insight into *how* |
| Line-by-line tracers | Python Tutor | Memory per step, but no macro control-flow picture |
| Algorithm animators | VisuAlgo | Great visuals, but only pre-built algorithms, not *your* code |

> [!warning] Competitive check (do before launch)
> Python Tutor also supports Java and other languages (from memory — verify). So the differentiator is **not** "Java tracing"; it is **macro CFG + data-structure visuals on arbitrary code, in a polished IDE**. Re-check competitors before positioning copy is written. Track in [[Risk Register]] (R-20).

## Solution
Combine all three: run *your* code, show a live CFG with branch states, and draw DSA structures automatically. See [[Project Overview]].

## What must be excellent (the "main sauce")
1. Correct, trustworthy CFG highlighting (incl. untaken branches) → [[CFG Model and Line Mapping]]
2. Automatic, accurate data-structure pictures → [[F05 Heap Renderer Catalog]]
3. Smooth replay with step-back → [[F02 Playback Controller]]
