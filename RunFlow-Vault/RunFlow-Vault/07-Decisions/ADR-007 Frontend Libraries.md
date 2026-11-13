---
tags: [runflow/adr]
status: accepted
---
# ADR-007 — Frontend libraries (and open question on API language)

**Decision:** React 18 + TypeScript + Vite; Zustand; `@monaco-editor/react`; `react-resizable-panels`; `@xyflow/react` + **Dagre** (ELKjs only if needed); `d3-hierarchy` (trees) and `d3-force` (graphs); custom SVG/React renderers; sanitized `<pre>` console (no xterm in MVP); zod validation.

**Why:** proven, MIT-friendly stack; minimal complexity; avoids xterm escape-injection surface; Dagre is simpler than ELK.

**Open question OQ-1 — API language:** Node/Fastify (one language with the UI, 2 languages total with Java tracer) vs Java (e.g., Javalin; one backend language, but a second ecosystem beside TS). **Default: Node/Fastify.** Decide in [[Phase 0 - Setup]]; record the outcome here.

**Licensing note:** check licenses of all dependencies (e.g., ELKjs is EPL-2.0 if adopted; JavaParser LGPL/Apache dual) before launch ([[Public Launch Gate]]).
Related: [[Tech Stack]], [[Frontend Architecture]].
