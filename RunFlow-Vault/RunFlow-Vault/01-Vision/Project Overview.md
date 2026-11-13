---
tags: [runflow/vision]
status: active
---
# Project Overview

## In simple terms
A website where a student pastes a Java program, clicks **Run Flow**, and watches it run as a visual story instead of just printed output.

## What the student sees
1. **Code** with a moving highlighted line.
2. **Flowchart** of the code: diamonds (decisions/loops), boxes (actions). Current = blue glow, visited = green, never-run = grey, crashed = red.
3. **Memory**: variables table, call stack, and pictures of data structures (arrays as cells, linked lists as boxes+arrows, trees as trees…). Pointers like `i`, `low`, `high` appear under the cell they index.
4. **Console** with program output.
5. **Playback bar**: play, pause, step back/forward, speed, scrubber.

## How it works (plain English)
1. Student's code goes to the server.
2. In a locked-down sandbox, the code is analysed (flowchart skeleton), compiled, and run under a debugger that records every step.
3. The recording (one JSON file) returns to the browser.
4. The browser replays the recording — that's why stepping backward is instant.

## Scope
Java 17, DSA topics only. See [[MVP Scope]]. Architecture: [[System Topology]].

## Success looks like
A learner can paste *their own* binary search / linked-list / BFS and understand what happened at each step, including which branches never ran. See [[MVP Definition of Done]].
