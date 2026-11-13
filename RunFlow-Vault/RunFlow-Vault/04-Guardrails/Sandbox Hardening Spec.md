---
tags: [runflow/guardrail, runflow/architecture]
status: draft
---
# Sandbox Hardening Spec

## `docker run` (single source of truth: `sandbox/run-sandbox.sh`)
```bash
docker run --rm -i \
  --name rf-$(uuid) --label runflow=1 \
  --runtime=runsc \
  --network none --ipc none \
  --memory=512m --memory-swap=512m \
  --cpus=1.0 \
  --pids-limit=128 \
  --ulimit nofile=256:256 --ulimit core=0 \
  --read-only \
  --tmpfs /tmp:rw,noexec,nosuid,nodev,size=64m \
  --cap-drop=ALL \
  --security-opt=no-new-privileges \
  runflow-sandbox:<pinned-digest>
```
> [!note] Initial numbers — calibrate in Spike S4. Two JVMs need more than the blueprint's 256 MB / 64 PIDs. Record final values in [[Execution Limits and Caps]].

## Process model (Spike S3 decides)
Goal: user code **cannot** signal, ptrace, read memory of, or talk to the tracer except through the intended debug link.
| Option | Idea | Notes |
|---|---|---|
| **A (default)** | Entrypoint shim starts as tracer uid (1001); target JVM launched under uid 1000 via `setpriv --reuid/--regid` with a minimal capability dance, then caps dropped | Works in one container; need to verify `--cap-drop=ALL` + setuid interplay under gVisor |
| B | Two containers sharing a network namespace (pod) | More infra; Kubernetes-friendly |
| C | Same uid for private beta only | **Not allowed for public launch** ([[Public Launch Gate]]) |

Additional rules:
- Class output dir `/tmp/classes` owned by tracer, **read-only** for target uid.
- JDWP via launching connector on **loopback with random port**; tracer validates all values from target as untrusted (sizes, types).
- Container stdout = trace only; target's stdout/stderr read by tracer via the process streams ([[ADR-004 Trace Channel Separate From User Stdout]]).
- Source arrives on stdin as JSON; **no volumes, no bind mounts**.

## Image
- Minimal jlink runtime incl. `java.base, java.compiler, jdk.compiler, jdk.jdi, jdk.jdwp.agent` (+ what Spike S3/S4 proves necessary). No shell tools beyond the entry shim (`setpriv`) if Option A.
- Pin image by **digest**; rebuild regularly; scan in CI ([[Risk Register]] R-14).
- Non-root default user; no package manager in final image.

## JVM flags
Tracer: `-Xmx96m -XX:+UseSerialGC`. Target: `-Xmx64m -Xss1m -XX:+UseSerialGC -XX:TieredStopAtLevel=1 -XX:-UsePerfData`. (Calibrate in S4.) Compiler: `-g -proc:none -implicit:none`.

## External watchdog (API side)
- Hard wall-clock timer per run → `docker kill`.
- Stdout byte cap → kill on exceed.
- Reaper job every 30 s: kill containers labeled `runflow=1` older than hard limit + grace.

## Host exposure (accepted MVP risk R-03)
Orchestrator needs to start containers = effective root. Mitigate: dedicated VM for sandboxing, nothing else on it, no secrets, egress-locked, API runs as unprivileged user with access to a **narrow spawner** (or rootless Docker/Podman + runsc if feasible), automatic OS patching, snapshot/rebuild capability. Post-MVP: Kubernetes + gVisor RuntimeClass or Firecracker.

## Verification
All items in [[Adversarial Test Corpus]] pass; manual review checklist in [[Public Launch Gate]].
