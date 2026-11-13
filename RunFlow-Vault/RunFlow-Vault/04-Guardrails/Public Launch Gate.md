---
tags: [runflow/guardrail, runflow/phase]
status: active
---
# Public Launch Gate

> [!danger] No public URL until **every** box is ticked. Private/localhost use is fine before this.

## Sandbox
- [ ] Tracer and target run under different uids (S3 option A or B) and tests A13/A14 pass
- [ ] External watchdog kills runs at hard time limit; single-line loop test passes (A01)
- [ ] `--network none`, read-only root, caps dropped, no-new-privileges verified via `docker inspect`
- [ ] Memory/PID/CPU limits calibrated (S4) and bomb tests pass (A04, A05, A11, A12)
- [ ] Container is destroyed after every run; reaper works (kill orchestrator mid-run → no orphans)
- [ ] Sandbox image pinned by digest, scanned, minimal
- [ ] Sandbox host dedicated, no secrets, egress locked, auto-patching on
- [ ] Docker socket exposure handled per [[Sandbox Hardening Spec]]

## API
- [ ] Rate limits, concurrency & queue caps active; load test passes
- [ ] Body/field caps enforced; oversized input rejected
- [ ] Output caps enforced at tracer **and** API
- [ ] Trace schema-validated server-side before return
- [ ] CORS locked; HTTPS only; HSTS
- [ ] Kill switch tested
- [ ] Logs contain no source/PII; retention set

## Frontend
- [ ] No `innerHTML`-style APIs (lint-enforced)
- [ ] CSP deployed and tested with Monaco
- [ ] XSS corpus A19–A22 inert
- [ ] Error boundaries and friendly errors for every status ([[F09 Errors and Exceptions UX]])

## Product
- [ ] MVP DoD met ([[MVP Definition of Done]])
- [ ] Privacy notice + usage limits message visible
- [ ] Monitoring/alerts live; on-call = you, with a runbook ("how to disable, how to rebuild host")
- [ ] Rollback plan; backups of config (no user data to back up)
- [ ] Competitor/positioning re-check done ([[Problem and Differentiator]])

## Sign-off
Date: ____ · By: ____ · Link to test report: ____
