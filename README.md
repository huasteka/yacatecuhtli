# Yacatecuhtli

[![Travis](https://img.shields.io/travis/murilocosta/yacatecuhtli.svg?style=flat-square)](https://travis-ci.org/murilocosta/yacatecuhtli)
[![GitHub issues](https://img.shields.io/github/issues/murilocosta/yacatecuhtli.svg?style=flat-square)](https://github.com/murilocosta/yacatecuhtli/issues)
[![Codacy grade](https://img.shields.io/codacy/grade/b324cad1175442aba9d46d20c9edad7e.svg?style=flat-square)](https://www.codacy.com/app/murilocosta/yacatecuhtli/dashboard)
[![Codecov](https://img.shields.io/codecov/c/github/murilocosta/yacatecuhtli.svg?style=flat-square)](https://codecov.io/gh/murilocosta/yacatecuhtli)

Yacatecuhtli is an open source lightweight financial management API developed with [Spring Boot](https://projects.spring.io/spring-boot).

## Setup

The minimum requirements are:

- [PostgreSQL](http://www.postgresql.org) (>= 9.3)
- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Installation

To download and build the project, open a terminal and execute:

```
git clone https://github.com/murilocosta/yacatecuhtli.git
cd yacatecuhtli
mvnw clean install
```

The deployable artifact will be created at `target` directory.

## Configuration

Yacatecuhtli uses Spring Boot which has a configuration file located at `src/main/resources/application.properties` 
that configures the application context with parameters you can find at the [documentation](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

## Tests

To execute all tests, open a terminal and execute:

```
mvnw test
```

## License

Yacatecuhtli is Copyright © 2017 Murilo Costa.

It is free software, and may be redistributed under the terms specified in the [LICENSE.md](LICENSE.md)
