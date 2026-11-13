---
tags: [runflow/backlog]
status: draft
---
# Post-MVP Parking Lot

> [!note] Ideas live here, not in the MVP. Revisit after [[MVP Definition of Done]]. Will become new vault folders (e.g., `10-v1.1-Python`).

- **Python** (client-side Pyodide, `sys.settrace`) — run in a sandboxed iframe on a **separate origin**, never in the app origin
- **JavaScript/TS** (Web Worker + AST instrumentation)
- **C/C++** (GDB/LLDB MI) — much larger escape surface; heavy hardening needed
- **Verilog/HDL** — clock-based model, needs a different engine than line tracing (see mockup); beware `$system`/`$fopen`
- Interactive live stdin (WebSocket, pause-aware timer)
- Shareable traces / educator links (needs XSS review + moderation)
- Accounts, saved snippets, history
- Breakpoints, step-over/step-out
- Multithreaded program tracing
- Redis warm container pool, AppCDS, latency <1s
- Light theme, mobile layout, i18n
- Export trace as GIF/video
- Competitor re-check and positioning ([[Problem and Differentiator]])
