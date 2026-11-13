---
tags: [runflow/test, runflow/guardrail]
status: draft
---
# Adversarial Test Corpus

Store as `testcorpus/adversarial/<id>/Main.java`. **Run only inside the sandbox** (never on your dev machine). Expected = what a *correct, safe* system does.

| ID | Attack / case | Expected |
|---|---|---|
| A01 | `while(true){}` on a single line | External watchdog → `TIME_LIMIT_EXCEEDED`; UI banner; no hang |
| A02 | Infinite loop over multiple lines | `STEP_LIMIT_EXCEEDED`, partial trace playable |
| A03 | Print in a loop (stdout flood) | `OUTPUT_LIMIT_EXCEEDED`, bounded memory |
| A04 | `new int[Integer.MAX_VALUE]` | `RUNTIME_EXCEPTION` (OOM) reported; host unaffected |
| A05 | Allocate lists until OOM | `MEMORY_LIMIT_EXCEEDED`; container killed cleanly |
| A06 | Infinite recursion | `RECURSION_DEPTH_LIMIT` / StackOverflowError handled |
| A07 | `System.exit(0)` mid-run | Graceful end with partial trace + note |
| A08 | `Runtime.getRuntime().exec("id")` / `ProcessBuilder` | Friendly `REJECTED` pre-check **and** blocked if bypassed (no host effect) |
| A09 | Read `/etc/passwd`, list `/`, write files | Fails/limited; read-only fs; nothing persists |
| A10 | `new Socket("example.com", 80)` / HTTP client | Fails; no network |
| A11 | Spawn 1,000 threads | PID limit → clean failure; host fine |
| A12 | Process/fork bomb attempt | Blocked by PID limit/ulimit |
| A13 | Scan loopback ports; connect to JDWP port; send JDWP packets | Cannot alter or crash trace (needs S3 isolation) |
| A14 | Try to kill/ptrace the tracer; read `/proc/<pid>/mem`, `/proc/<pid>/environ` | Denied (different uid) |
| A15 | `"x".repeat(100_000_000)` | Memory limit or truncation; UI inert |
| A16 | Compile bomb: 5,000 nested parentheses / huge generic chain | Compile timeout/failure, tracer survives |
| A17 | Source > 20 KB | Rejected client + API |
| A18 | Print forged trace JSON / delimiter strings to stdout | Appears only as console text; trace unchanged |
| A19 | Strings/class names like `<img src=x onerror=alert(1)>`, `</script>` | Rendered inert everywhere |
| A20 | Static initializer infinite loop | `TIME_LIMIT_EXCEEDED` |
| A21 | `System.setOut(null)` / close `System.out` / `System.setErr` | Trace intact |
| A22 | ANSI escapes (`\u001b[2J`, OSC), bidi override chars in output | Stripped/visualized |
| A23 | Annotation processor / `@Retention` tricks | `-proc:none`; no code executed at compile |
| A24 | Extra threads writing while main is traced | No crash; note shown |
| A25 | `ArrayList` with 1,000,000 elements | Preview capped; snapshot small |
| A26 | Cyclic/self-referencing structures (`a.next = a`) | No infinite recursion in tracer/UI |
| A27 | `Scanner` with no stdin | `NoSuchElementException` + hint |
| A28 | Reflection / `setAccessible` / `Unsafe` attempts | Contained; no escape; no tracer impact |
| A29 | `Thread.sleep(60000)` | `TIME_LIMIT_EXCEEDED` (wall-clock) |
| A30 | Huge numbers of tiny objects (500k nodes) | Object cap + truncation; memory bounded |
| A31 | 100 parallel requests from one IP | Rate limit/queue → 429/503, host healthy |
| A32 | Kill API mid-run | No orphan containers after reaper |

## Rules
- Any new attack idea → add a row + test.
- All must pass before [[Public Launch Gate]]. Related: [[Threat Model]], [[Execution Limits and Caps]], [[Frontend Safety]], [[Test Strategy]].
