# Lab 14 – Maven + Git Workflow Demo

This project is a small calculator used to demonstrate Git flow, GitHub Actions CI, Checkstyle, JUnit 5, and JaCoCo 100% branch coverage.

## Build and test
- `mvn test` – run JUnit 5 tests
- `mvn checkstyle:check` – enforce the Checkstyle rules in `checkstyle.xml`
- `mvn jacoco:report` – generate HTML coverage report under `target/site/jacoco/index.html`
- `mvn jacoco:check` – enforce 100% branch coverage (used by CI)

## Project layout
- Source: `src/main/java/lab14/sict/must/edu/mn`
- Tests: `src/test/java/lab14/sict/must/edu/mn`
- CI workflow: `.github/workflows/ci.yml` (runs on pushes/PRs to `develop` and `release/*`)

## Git workflow (to apply on GitHub)
1. Protect `main` branch: require PRs, require status checks (CI workflow), require up-to-date branches, disallow bypass.
2. Work on `develop`, create feature branches (e.g., `feature/add-multiplication`, `feature/add-division`), merge via PR with “Create a merge commit”.
3. Create release branches (e.g., `release/v1.0.0`) and tags (`v1.0.0`, `v1.0.1`), plus hotfix branches when needed.
4. Document any merge conflict resolutions in `conflict_resolution.md`.
