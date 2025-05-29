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

Give it some time to spin up and then open up ```http://localhost:8080/api/spark/word-count``` in a browser

You'll know everything is working correctly if you get the following response:

```
[{"word":"Hello","count":4},{"word":"Spark","count":2},{"word":"World","count":1},{"word":"Spring","count":1}]
```