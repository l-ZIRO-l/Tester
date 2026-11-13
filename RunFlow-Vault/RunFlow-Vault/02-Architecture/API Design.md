---
tags: [runflow/architecture]
status: draft
---
# API Design (MVP)

## Endpoints
| Method | Path | Purpose |
|---|---|---|
| `POST` | `/api/v1/visualize` | Run + trace a program, return the trace |
| `GET` | `/api/v1/health` | Liveness (no sandbox work) |
| `GET` | `/api/v1/presets` | (optional) list preset programs; can be static files in the web app |

## `POST /api/v1/visualize`
Request:
```json
{ "language": "java", "source": "public class Main { ... }", "stdin": "" }
```
Validation (reject early, HTTP 4xx): `language == "java"`, `source` ≤ 20 KB, `stdin` ≤ 4 KB, valid UTF-8, no NUL bytes, JSON body ≤ 32 KB.

Responses
- `200` — trace JSON ([[Trace JSON Contract]]), including non-SUCCESS statuses with partial traces (compile errors, limits, exceptions are **expected outcomes**, not HTTP errors)
- `400` bad request · `413` too large · `429` rate-limited (with `Retry-After`) · `503` queue full / overloaded · `500` internal

## Orchestration flow
1. Validate + rate-limit ([[API Abuse Protection]]).
2. Acquire a concurrency slot (max N concurrent sandboxes; bounded queue; wait ≤ 5 s else 503).
3. `run-sandbox.sh` with random container name + label `runflow=1`; write request JSON to container stdin.
4. Read container stdout with a **byte cap**; start **external hard timer** (kills container).
5. On exit: parse + validate trace against schema; cap size; return. Always remove container (`--rm` + reaper).
6. Log: request id, source hash, status, timings (no source stored by default).

## Don'ts
- Don't expose container errors verbatim to users (generic `SANDBOX_ERROR`; details in server logs).
- Don't run the API as root; don't mount host paths into the sandbox.
- Docker socket access is high-risk → [[Sandbox Hardening Spec]] §Host exposure.
