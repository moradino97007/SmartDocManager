# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

This is a Gradle/Spring Boot project. Use the wrapper (`gradlew.bat` on Windows, `./gradlew` in bash).

- Build: `./gradlew build`
- Run the app: `./gradlew bootRun` (starts on the default port, backed by an in-memory H2 database)
- Run all tests: `./gradlew test`
- Run a single test class: `./gradlew test --tests "com.smartdocmanager.SmartDocManagerApplicationTests"`
- H2 console (when app is running): `/h2-console`, JDBC URL `jdbc:h2:mem:smartdocdb`, user `sa`, no password (see `src/main/resources/application.yaml`)

## Architecture

The app follows a hexagonal (ports & adapters) structure under `com.smartdocmanager`:

- `domain.model.Document` — immutable domain object (final fields, no setters). Business rules live here, e.g. `isSummaryEligible()` (content longer than 200 chars is "summary eligible") and the `createNew(title, author, content)` factory method.
- `domain.port.output.DocumentOutputPort` — the output port interface the domain depends on. The domain/service layer must never depend on JPA/repository types directly — only on this interface.
- `domain.sevice.DocumentService` — application service, orchestrates business logic and calls the output port. Note the package is spelled `sevice` (not `service`) throughout the codebase — keep this spelling when adding files here rather than "fixing" it, since the existing package name is used elsewhere.
- `domain.exeption.DocumentNotFoundException` — similarly, this package is spelled `exeption` (not `exception`).
- `infrastructure.adapter.DocumentJpaAdapter` — implements `DocumentOutputPort`, maps between `Document` (domain) and `DocumentEntity` (JPA), and delegates to `DocumentJpaRepository`.
- `infrastructure.adapter.out.persistance.*` — JPA entity (`DocumentEntity`) and Spring Data repository (`DocumentJpaRepository`). Package is spelled `persistance`.
- `infrastructure.adapter.in.web.*` — `DocumentController` (REST API under `/api/v1/documents`), request/response DTOs (`dto.DocumentRequest`, `dto.DocumentResponse`), and `GlobalExceptionHandler`/`ErrorResponse` for mapping validation errors and `DocumentNotFoundException` to HTTP responses.
- `DataLoader` — a `CommandLineRunner` that seeds one `DocumentEntity` into H2 on every application startup.

Data flow for a request: Controller receives a DTO → calls `DocumentService` → service applies domain rules and calls `DocumentOutputPort` → `DocumentJpaAdapter` converts to/from `DocumentEntity` and persists via `DocumentJpaRepository`. Controllers and adapters are responsible for all mapping between domain objects, entities, and DTOs — the domain model itself has no knowledge of JPA or HTTP.

### Known gotcha

`DocumentService.createDocument` calls `Document.createNew(document.title(), document.content(), document.author())`, but `Document.createNew`'s signature is `(title, author, content)` — the `content` and `author` arguments are passed in swapped order. Be aware of this if you're debugging why a created document's author/content fields look wrong, or touch either method.
