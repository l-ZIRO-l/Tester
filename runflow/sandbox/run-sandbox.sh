#!/usr/bin/env bash
# Single source of truth for docker sandbox flags (vault: Sandbox Hardening Spec).
# Run only on the dev VM. Use with gVisor: add --runtime=runsc when available.
set -euo pipefail
IMAGE="${IMAGE:-runflow-sandbox:dev}"
SRC_DIR="${1:?usage: run-sandbox.sh <dir-with-Main.java>}"
docker build -t "$IMAGE" "$SRC_DIR"
exec docker run \
  --runtime=runsc \
  --rm -i \
  --network=none \
  --read-only \
  --cap-drop=ALL \
  --security-opt=no-new-privileges \
  --pids-limit=64 \
  --memory=256m \
  --memory-swap=256m \
  --cpus=1 \
  --tmpfs /tmp:size=16m \
  -w /sandbox \
  "$IMAGE"
