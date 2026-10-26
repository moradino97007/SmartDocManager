---
name: commit-message
description: Writes clear, effective git commit messages in English following the Conventional Commits standard. Use right before running "git commit -m", after the user has given explicit approval to commit (FGO / feu vert) — never to decide on its own whether to commit.
---

# Commit message guidelines

Only use this skill when the user has explicitly approved creating a commit. It governs the **wording** of the commit message, not the decision to commit.

## Format (Conventional Commits)

```
<type>(<optional scope>): <short summary in imperative mood>

<optional body>
```

- **type**: one of `feat`, `fix`, `refactor`, `test`, `docs`, `chore`, `build`, `ci`, `perf`, `style`.
- **scope**: optional, lowercase, names the affected area (e.g. `document`, `controller`, `jpa-adapter`).
- **summary**:
  - English, imperative mood ("add", "fix", "rename" — not "added", "adds", "fixing").
  - No capital letter requirement, no trailing period.
  - Max ~72 characters.
  - States *what* changed and *why* it matters, not a changelog of every edited line.
- **body** (only when the change needs explaining beyond the summary):
  - Wrap at ~72 chars per line.
  - Explain the motivation/context, not a restatement of the diff.
  - Use a bullet list for multiple distinct points.

## Process

1. Run `git status` and `git diff --staged` (or `git diff` if nothing is staged yet) to see the actual changes — never guess the message from memory of the conversation alone.
2. Pick the single `type` that best matches the dominant change. If the diff mixes unrelated concerns, say so and suggest splitting into separate commits rather than forcing one type.
3. Write the summary line first; add a body only if the summary alone would leave a reviewer confused about the motivation.
4. Pass the message via a heredoc (not a bare `-m "..."` for multi-line messages) to preserve formatting exactly.

## Examples

```
feat(document): add summary-eligibility check to Document model
```

```
fix(service): correct swapped author/content arguments in createDocument
```

```
refactor(adapter): extract entity-to-domain mapping into private methods
```

```
chore: add gradle wrapper and gitignore for initial project setup
```
