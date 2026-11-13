---
tags: [runflow/phase]
status: draft
---
# Phase 0 — Setup

**Goal:** a clean base so later work is fast and safe.

## Tasks
- [ ] Create monorepo per [[Repo Structure]]; init git; `.gitignore`; license decision
- [ ] Choose Java build tool (Gradle or Maven); JDK 17 locally (`javac`, `jdk.jdi` available: `java --list-modules | grep jdi`)
- [ ] Node 20 + pnpm/npm; Vite React TS app skeleton; ESLint (ban `innerHTML` APIs) + Prettier
- [ ] CI (GitHub Actions): lint, unit tests, dependency audit
- [ ] Docker + gVisor installed on a **dev VM** (not your main machine) and `runsc` smoke test (`docker run --runtime=runsc hello-world`)
- [ ] Write the **first 10 DSA test programs** into `testcorpus/dsa/` ([[DSA Test Corpus]])
- [ ] Write 10 adversarial programs ([[Adversarial Test Corpus]]) — just the sources for now
- [ ] Draft `schemas/trace.schema.json` from [[Trace JSON Contract]] (v0)
- [ ] Hand-write one **fake trace JSON** for binary search (used by UI later)
- [ ] Re-check competitors (Python Tutor Java support, etc.) → update [[Problem and Differentiator]] (R-20)
- [ ] Decide open question OQ-1 (API language) → [[ADR-007 Frontend Libraries]]

## Exit criteria
- Repo builds; CI green; corpus files exist; fake trace validates against schema.

## Guardrails to apply now
Pin dependencies; commit lockfiles; no secrets in repo ([[Risk Register]] R-14).

Next: [[Phase 1 - Spikes]]
