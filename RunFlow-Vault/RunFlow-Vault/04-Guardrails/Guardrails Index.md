---
tags: [runflow/guardrail]
status: active
---
# Guardrails Index

> [!danger] Principle
> **Every visitor is an attacker until proven otherwise.** RunFlow is, by design, a remote-code-execution service. Safety is a feature, built from day one — not Phase-8 polish.

| Area | Note |
|---|---|
| Who attacks what | [[Threat Model]] |
| Container/process isolation | [[Sandbox Hardening Spec]] |
| Time, steps, memory, output, payload | [[Execution Limits and Caps]] |
| XSS & rendering untrusted strings | [[Frontend Safety]] |
| Rate limits, abuse, cost | [[API Abuse Protection]] |
| Program edge cases (cycles, recursion, stdin…) | [[Edge Case Checklist]] |
| Tracked risks with owners | [[Risk Register]] |
| Gate before going public | [[Public Launch Gate]] |
| Attack test programs | [[Adversarial Test Corpus]] |

## Top 10 rules (memorize)
1. All untrusted source processing happens **inside** the sandbox ([[ADR-003 All Untrusted Processing Inside Sandbox]]).
2. Tracer and user code run as **different uids**; watchdog is **external** ([[Sandbox Hardening Spec]]).
3. One fresh container per run; destroyed afterwards. No reuse across users in MVP.
4. `--network none`, read-only root, caps dropped, resource limits on everything.
5. Never invoke user methods via JDI (`toString/hashCode/equals`).
6. Trace channel ≠ user stdout.
7. Hard caps on steps, time, output, payload, heap preview ([[Execution Limits and Caps]]).
8. Render user-derived text as inert text only ([[Frontend Safety]]).
9. Rate limit + concurrency cap + queue cap before public launch ([[API Abuse Protection]]).
10. No source stored by default; logs contain hashes/timings only.
