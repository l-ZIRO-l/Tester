---
tags: [runflow/phase]
status: draft
---
# Phase 8 — Hardening and Launch

**Goal:** pass the [[Public Launch Gate]].

## Tasks
- [ ] Rate limiting + abuse controls live ([[API Abuse Protection]]); optional CAPTCHA
- [ ] Load test: concurrency cap, queue behavior, graceful 503s
- [ ] Abuse test scripts: pool exhaustion, slow clients, oversized payloads, repeated bombs
- [ ] Security review pass over [[Threat Model]]; fix gaps; second pair of eyes if possible
- [ ] CSP + headers verified in production build
- [ ] Monitoring/alerts, runbook (disable, rebuild host, rotate keys)
- [ ] Patching automation for sandbox host and image rebuild schedule
- [ ] Accessibility pass (contrast, keyboard, icons for states)
- [ ] Browser matrix (latest Chrome, Firefox, Safari, Edge)
- [ ] Privacy notice, usage limits text, "report a problem" link
- [ ] Beta with a few friends/classmates; collect failing programs → add to corpus
- [ ] Fill [[Public Launch Gate]] sign-off; tag release

## Exit criteria
Launch gate fully ticked; [[MVP Definition of Done]] met.
