# gVisor setup (dev VM only — never your main machine)

1. Install Docker per distro docs.
2. Install `runsc` (https://gvisor.dev/docs) and register it in `/etc/docker/daemon.json`:
   ```json
   { "runtimes": { "runsc": { "path": "/usr/local/bin/runsc" } } }
   ```
3. `sudo systemctl restart docker`
4. Smoke test: `docker run --runtime=runsc hello-world`
5. Confirm fallback policy: if `runsc` is missing, `run-sandbox.sh` must FAIL (no silent runc).

Phase 0 exit criterion: smoke test passes on the dev VM. (Cannot be done from this container.)
