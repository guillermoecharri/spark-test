# Spark Test

## Overview

This is an example repo that shows how to run a Springboot API Server that connected to an Apache Spark instance running in Docker.

## Requirements

- You need Docker installed
- You need Java 17
- You need to have Maven installed

## How to run

Run the docker compose to start up spark with a couple workers

```
docker compose up -d
```

Install dependencies
```
mvn clean install
```

Run the spring boot server

```
mvn spring-boot:run
```