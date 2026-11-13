---
tags: [runflow/meta]
status: active
---
# Vault Conventions

## Folders
`00-Start` entry points · `01-Vision` why/what · `02-Architecture` how · `03-Features` specs · `04-Guardrails` safety · `05-Roadmap` build order · `06-Testing` · `07-Decisions` ADRs · `08-Templates` · `09-Journal`

## Notes
- Link with `[[Note Name]]` (filenames are unique across the vault).
- Frontmatter: `tags` and `status` (`draft | active | done | proposed | accepted | superseded`).
- Tags: `#runflow/feature`, `#runflow/guardrail`, `#runflow/adr`, `#runflow/phase`, `#runflow/test`.
- Task checkboxes `- [ ]` live in Phase notes; the [[Status Tracker]] summarizes.

## Workflow
1. Start a session → read [[Status Tracker]].
2. Work a phase's checklist; tick boxes in the phase note.
3. Decide something → copy [[Template ADR]] to `07-Decisions/` and add it to [[ADR Index]].
4. Finish a spike → copy [[Template Spike Report]] into the phase note or a new note; update ADRs.
5. Found a bug → [[Template Bug Report]].
6. End session → [[Template Daily Log]] entry in [[Daily Log]]; update [[Status Tracker]].

## Rules
- One idea per note. Prefer links over copy-paste.
- Numbers (limits, budgets) live in one place: [[Execution Limits and Caps]].
- After MVP ships, add new folders (e.g., `10-v1.1-Python`) rather than rewriting MVP notes.
