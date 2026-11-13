---
tags: [runflow/phase]
status: active
---
# Roadmap Overview (to MVP)

**Principle:** de-risk first, then build thin vertical slices. Each phase ends with something you can **demo**. Private/local until Phase 8.

| Phase | Goal | Size* | Exit demo |
|---|---|---|---|
| [[Phase 0 - Setup]] | Repo, tooling, test corpus seeds | S | `hello` trace JSON from a stub |
| [[Phase 1 - Spikes]] | Answer the 4 risky questions | M | Spike reports S1–S4 + ADR updates |
| [[Phase 2 - Tracer CLI]] | Real tracer producing valid traces | L | CLI turns 10 corpus programs into valid JSON |
| [[Phase 3 - UI Shell]] | Full workspace replaying a saved trace | L | Load JSON → editor + graph + panels + playback |
| [[Phase 4 - Tier 1 Visuals]] | Arrays, strings, recursion, DP, stack/queue | L | Bubble sort, binary search, fib, DP demo |
| [[Phase 5 - Tier 2 Visuals]] | Linked lists, trees, heaps | L | List reversal, BST, heap demo |
| [[Phase 6 - Tier 3 Visuals]] | Maps, graphs, tries | M–L | BFS/DFS, HashMap demo |
| [[Phase 7 - Sandbox and API]] | Real runs end-to-end, sandboxed | L | Paste code in UI → live trace via sandbox |
| [[Phase 8 - Hardening and Launch]] | Pass the launch gate | M | [[Public Launch Gate]] signed |

*S ≈ days, M ≈ 1–2 weeks, L ≈ 2–4 weeks part-time (rough; adjust after Phase 1).

## Ordering logic
1. Spikes first: if S1 fails, the product concept changes.
2. UI is built against **recorded JSON**, so UI and tracer progress in parallel without a server.
3. Visual tiers are independent slices → ship value early.
4. Sandbox/API only after the product works locally — but isolation design (S3) is proven early.

## Parallelism (if you have help)
Phase 2 (tracer) ∥ Phase 3 (UI) once the trace contract v1 is frozen after Phase 1.

## Done = [[MVP Definition of Done]]
