# GraphQL Contract Testing Using Specmatic

* [Specmatic Website](https://specmatic.in)
* [Specmatic Documenation](https://specmatic.in/documentation.html)

This sample project demonstrates how we can practice contract-driven development and contract testing in a GraphQL (Kotlin) API that depends on an external domain service. Here, Specmatic is used to stub calls to domain API service based on its OpenAPI specification.

### Architecture Diagram

![GraphQL BFF Architecture Diagram](./architecture.svg)

### Starting the server

To start the server, use this command:

- MacOS or Windows: `./gradlew bootRun`
- Windows: `gradlew bootRun`

You'll need the backend product API server running for this to work. You can get it from [here](https://github.com/znsio/specmatic-order-api-java). The README.md file in the repo contain instructions for starting up the backend API server.

### Running the contract tests

Use this command to run the contract tests:

- MacOS or Linux: `./gradlew test`
- Windows: `gradlew test`

These tests will just run, as the backend has been stubbed out using Specmatic.
