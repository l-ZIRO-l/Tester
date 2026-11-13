---
tags: [runflow/guardrail]
status: draft
---
# API Abuse Protection

An open `/visualize` is free remote compute. Protect cost, availability and the host.

## Controls (MVP)
| Control | Initial value |
|---|---|
| Per-IP rate limit | 10 runs/min, 100 runs/day (tunable) |
| Global concurrency | N = CPU cores − 1 sandboxes at once |
| Queue | ≤ 20 waiting, wait ≤ 5 s, else `503` + `Retry-After` |
| Body/field caps | see [[Execution Limits and Caps]] |
| CORS | allow only the web app origin |
| Bot protection | Turnstile/hCaptcha (enable if abuse seen; required if no other control) |
| Kill switch | env flag `RUNFLOW_DISABLED=1` returns `503` instantly |
| Cost alarm | alert on sustained 100% concurrency or spike in runs |

## Pool/spawn protection
Container creation is the scarce resource: count spawn failures; back off; never let one IP hold more than 1–2 slots.

## Privacy
- Do **not** store submitted source or traces by default.
- Log: request id, timestamp, source SHA-256, status, durations, IP hash (salted, rotating). Retain ≤ 14 days.
- Add a short privacy notice in the UI footer.

## Monitoring
Dashboards/alerts: error rate by status, p95 latency, sandbox kills, OOMs, queue depth, reaper kills, disk usage on host.

## Tests
Load test + abuse scripts in [[Phase 8 - Hardening and Launch]]; checklist in [[Public Launch Gate]].
