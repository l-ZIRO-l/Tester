---
tags: [runflow/architecture]
status: active
---
# Tech Stack (MVP)

| Area | Choice | Why / notes |
|---|---|---|
| Frontend | React 18 + TypeScript + Vite | Fast dev loop, strong typing for trace contract |
| State | Zustand | Small, good for playback cursor |
| Editor | `@monaco-editor/react` | Line decorations, markers for compile errors |
| Layout | `react-resizable-panels` | Draggable/collapsible 4-pane layout |
| Flow graph | `@xyflow/react` + `dagre` | Dagre first (simple); ELKjs only if layouts get messy |
| Tree layout | `d3-hierarchy` | Binary/n-ary trees, heaps-as-trees |
| Graph layout | `d3-force` (or ELK) | Graph visualizer (Tier 3) |
| Custom visuals | React + SVG (text via React, never innerHTML) | See [[Frontend Safety]] |
| Console | Sanitized `<pre>` | Avoid xterm escape-sequence injection in MVP |
| Validation | zod | Validate trace before render |
| API | Node 20 + Fastify (TS) | Orchestration; spawns containers |
| Tracer | Java 17 CLI jar (JavaParser, javax.tools, JDI) | JDI requires a JDK (`jdk.jdi`) |
| Build (Java) | Gradle or Maven | Pick one in [[Phase 0 - Setup]] |
| Sandbox | Docker + gVisor `runsc` | [[Sandbox Hardening Spec]] |
| Image | jlink'd minimal JDK runtime | Smaller attack surface; verify required modules in Spike S3/S4 |
| Tests | Vitest + Playwright (UI), JUnit (tracer), golden JSON traces | [[Test Strategy]] |
| CI | GitHub Actions | lint, test, dependency audit, image scan |

## Two languages?
TypeScript (web + API) and Java (tracer). Alternative: write the API in Java too (one backend language). Trade-off recorded in [[ADR-007 Frontend Libraries]] (Open question OQ-1: API language).

## Supply-chain guardrails
Pin versions, lockfiles committed, `npm audit`/`osv-scanner` in CI, scan the sandbox image, no postinstall scripts from unknown packages. See [[Risk Register]] (R-14).
