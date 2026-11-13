---
tags: [runflow/architecture]
status: draft
---
# Repo Structure (monorepo)

```
runflow/
├─ apps/
│  └─ web/                 # React + TS UI
│     └─ src/{components,panels,renderers,state,trace,lib}
├─ services/
│  ├─ api/                 # Node/Fastify orchestrator
│  └─ tracer/              # Java 17: parser, CFG builder, compiler, JDI tracer
├─ sandbox/
│  ├─ Dockerfile           # minimal runtime image
│  ├─ run-sandbox.sh       # the docker run flags (single source of truth)
│  └─ gvisor-setup.md
├─ testcorpus/
│  ├─ dsa/                 # Main.java files from [[DSA Test Corpus]]
│  ├─ adversarial/         # from [[Adversarial Test Corpus]]
│  └─ golden/              # expected trace JSON (reviewed by hand)
├─ schemas/
│  └─ trace.schema.json    # source of truth for [[Trace JSON Contract]]
└─ docs/                   # (this vault can live here or beside it)
```

## Rules
- `schemas/trace.schema.json` generates TS types **and** validates tracer output in CI.
- `sandbox/run-sandbox.sh` is the only place docker flags are defined ([[Sandbox Hardening Spec]]).
- Golden traces are reviewed manually once, then guard against regressions ([[Test Strategy]]).
