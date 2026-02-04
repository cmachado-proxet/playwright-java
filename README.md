# Playwright Java tests

## Overview

This folder contains Playwright-based UI tests written in Java and executed via Maven. The instructions below show how to run the suite, enable Playwright's debug inspector, and find test artifacts (videos, reports).

## Task

- Run and review the tests in `src/test/java/com/mycompany/app/AppTest.javs`. Not all tests must pass.
- Provide clear, small, well-tested changes. Prioritize correctness and readable code.

## What we expect from you

- Run and inspect the tests in `src/test/java/com/mycompany/app/AppTest.jav` to understand the expected behavior.
- Make minimal, well-scoped changes to improve test reliability or correctness. Aim to make as many tests pass as reasonably possible.

## Acceptance Criteria

- Most tests should pass when running `./mvnw clean test`. It's acceptable for some tests to remain failing if they fail due to application bugs.
- Changes are small, follow the existing style, and include a short explanation of the approach you took.

## Discussion & reporting failing tests

We expect a short conversation about any failing tests you encounter.

- **Observed result:** error message or assertion failure (copy the terminal output or error snippet)
- **Artifacts:** paths to any screenshots, videos, traces, or HTML reports produced (see `target/videos` and `target/surefire-reports`)
- **Likely cause:** one of: `application-bug`, `test-bug`, `flaky`, `environment-issue`, or `unknown`
- **Suggested next step:** short recommendation (fix test, open issue, mark as flaky, skip with note)

Why this matters: a focused summary helps us quickly decide whether the failure is expected (an application bug we don't expect you to fix in the exercise) or something that should be addressed.

## Time Guidance

- This exercise is intended to take ~15-20 minutes. Focus on delivering a clear, working solution rather than perfect polish.

## Prerequisites

- Java (JDK 17+ recommended)
- Maven (or use the bundled `mvnw`/`mvnw.cmd` wrappers)

## Quick commands

- Install browsers:

```bash
./mvnw exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

- Run the full test suite (headless):

```bash
./mvnw clean test
```

- Run tests with Playwright inspector (headed / interactive):

```bash
PWDEBUG=1 PLAYWRIGHT_JAVA_SRC="src/test/java" ./mvnw clean test
```

- Generate a surefire report / HTML summary after tests:

```bash
./mvnw surefire-report:report
```

- Run a single test class or method:

```bash
./mvnw -Dtest=com.mycompany.app.AppTest test
./mvnw -Dtest=com.mycompany.app.AppTest#shouldSubmitForm test
```

## Environment variables explained

- `PWDEBUG=1` — opens Playwright Inspector and runs the browser in headed mode.
- `PLAYWRIGHT_JAVA_SRC` — points Playwright to the Java test sources (used by some debug flows).

## Artifacts & results

- Video recordings and other Playwright artifacts are saved under `videos/`.
- Per-run test metadata is under `test-results/`.
- Maven Surefire HTML reports live in the target site when generated via `surefire-report:report`.

## Test Files

- `src/test/java/com/mycompany/app/AppTest.java` - Contains all test cases

## Troubleshooting

- If browsers are not installed, run the Playwright install step according to the [Playwright for Java docs](https://playwright.dev/java/docs/browsers) so the required browsers are downloaded.
- If tests hang when using `PWDEBUG`, try running without the environment variable to confirm headless execution first.

## Notes

- Tests live under `src/test/java` and are executed by the Maven `test` lifecycle.
- Use the Maven wrapper `./mvnw` on macOS/Linux to ensure consistent Maven version across environments.
