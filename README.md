# Fuzzy Search with Postgres Trigram

This demo project shows how to use Spring Boot (Kotlin), Spring Data JPA, and PostgreSQL's trigram search for fuzzy text search. The application demonstrates how to integrate PostgreSQL's powerful text search features (pg_trgm) into a modern Spring application and expose them via a REST API.

## Prerequisites

- Java 21
- Docker (for the local PostgreSQL instance via Docker Compose)
- macOS or Linux recommended

## Setup & Start

**1. Start the Spring Boot application**:

To run the application, in `trigram-demo` use:

```sh
./gradlew bootRun
```

The application will be available at http://localhost:8080.

**2. Populate Test Data**:
Populate test data by executing one of these scripts:

- `src/main/resources/sql-scripts/populate_sample_data.sql`
- `src/main/resources/sql-scripts/populate_sample_data_accents.sql`

## Testing

To run the tests, in `trigram-demo` use:

```sh
./gradlew test
```

## Example API Call

Hit the search endpoint and provide the search input via the `q` query param:

```sh
curl 'http://localhost:8080/customers?q=Barcelona'
```

## Notes

- A postgres database is created automatically on startup of the service
- Database connection and credentials can be found in `trigram-demo/src/main/resources/compose.yaml`
- Flyway is used to for database migrations and creation of the trigram extension on startup
- Sample data is **not** loaded on startup, populate test data manually (see step 2 above)
- This project is meant for demonstrative purposes only and must not be used in production
