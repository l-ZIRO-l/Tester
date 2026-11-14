---
tags: [runflow/phase]
status: draft
---
# Phase 0 — Setup

**Goal:** a clean base so later work is fast and safe.

## Tasks
- [x] Create monorepo per [[Repo Structure]]; init git; `.gitignore`; license decision → done 2026-11-14 in `/workspace/runflow/` (license: all-rights-reserved while private; revisit at launch gate)
- [x] Choose Java build tool (**Gradle**, toolchain pinned to JDK 17); verified locally: `javac` present, `jdk.jdi@17.0.20` in `java --list-modules`
- [x] Node 20 + npm; Vite React TS app skeleton (`apps/web`, hand-scaffolded to avoid interactive prompts); ESLint flat config bans `innerHTML`/`outerHTML`/`dangerouslySetInnerHTML`/`document.write`. Prettier deferred (nice-to-have, not exit-blocking)
- [x] CI (GitHub Actions) seeded: `.github/workflows/ci.yml` — web lint/typecheck/build/audit + corpus compile + trace-schema validation. Actual green run requires pushing to GitHub
- [ ] Docker + gVisor installed on a **dev VM** (not your main machine) and `runsc` smoke test (`docker run --runtime=runsc hello-world`) — **manual step**: no Docker in this environment; seeds written to `sandbox/` (`Dockerfile`, `run-sandbox.sh`, `gvisor-setup.md`)
- [x] Write the **first 10 DSA test programs** into `testcorpus/dsa/` ([[DSA Test Corpus]]) → 10 programs written & verified with javac/java 17: T1-01, T1-02, T1-03, T1-06, T1-11, T1-12, T1-13 (Queue), T2-01, T2-05, T3-03
- [x] Write 10 adversarial programs ([[Adversarial Test Corpus]]) — sources only: A01–A10 in `testcorpus/adversarial/` (never run outside sandbox)
- [x] Draft `schemas/trace.schema.json` from [[Trace JSON Contract]] (v0)
- [x] Hand-write one **fake trace JSON** for binary search → `testcorpus/golden/binary-search.fake.trace.json` (validates against schema v0, 0 errors)
- [ ] Re-check competitors (Python Tutor Java support, etc.) → update [[Problem and Differentiator]] (R-20) — needs live web research
- [x] Decide open question OQ-1 (API language) → **Node/Fastify confirmed**; recorded in [[ADR-007 Frontend Libraries]]

## Exit criteria
- [x] Repo builds (web `tsc && vite build` OK; tracer stub compiles)
- [ ] CI green (workflow seeded; needs GitHub push to observe first green run)
- [x] Corpus files exist (9 DSA + 10 adversarial)
- [x] Fake trace validates against schema (also the Phase 0 demo: `HelloTrace` stub emits a schema-valid "hello" trace JSON)

## Guardrails to apply now
Pin dependencies; commit lockfiles; no secrets in repo ([[Risk Register]] R-14).

Next: [[Phase 1 - Spikes]]
