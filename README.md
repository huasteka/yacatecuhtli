# Yacatecuhtli

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/huasteka/yacatecuhtli/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/huasteka/yacatecuhtli/tree/master)
[![Maintainability](https://api.codeclimate.com/v1/badges/c9de92281b03967cc886/maintainability)](https://codeclimate.com/github/huasteka/yacatecuhtli/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/c9de92281b03967cc886/test_coverage)](https://codeclimate.com/github/huasteka/yacatecuhtli/test_coverage)

Yacatecuhtli is an open source lightweight financial management API developed with [Spring Boot](https://projects.spring.io/spring-boot).

## Setup

The minimum requirements are:

- [PostgreSQL](http://www.postgresql.org) (>= 9.3)
- [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Installation

To download and build the project, open a terminal and execute:

```
git clone https://github.com/huasteka/yacatecuhtli.git
cd yacatecuhtli
mvnw clean install
```

The deployable artifact will be created at `target` directory.

## Configuration

Yacatecuhtli uses Spring Boot which has a configuration file located at `src/main/resources/application.properties` 
that configures the application context with parameters you can find at the [documentation](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

## Tests

To execute all tests, open a terminal and execute:

```sh
mvnw test
```

## Run

To run the application, open a terminal and execute:

```sh
mvnw spring-boot:run
```

## License

Yacatecuhtli is Copyright © 2017 Huasteka.

It is free software, and may be redistributed under the terms specified in the [LICENSE.md](LICENSE.md)
