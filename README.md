[![Build Status](https://travis-ci.org/Cute-DB/cute-db-server.svg?branch=master)](https://travis-ci.org/Cute-DB/cute-db-server) [![Dependency Status](https://www.versioneye.com/user/projects/5760a1654931050036a5e9c1/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5760a1654931050036a5e9c1)

<a href="https://zenhub.com"><img src="https://raw.githubusercontent.com/ZenHubIO/support/master/zenhub-badge.png"></a>

# cute-db-server

## Quick Start

```bash
bower install
mvn clean package
java -jar target/cutedbserver-x.x.x.jar
```

## Useful curl commands

### List runs
```bash
curl http://localhost:9000/runs
```
### Add a run
```bash
curl -X PUT -H "Content-Type:application/json" -d '{ "jdbcUrl": "jdbc:postgresql://localhost:5432/dbtest", "server": "postgres", "status": "PENDING" }' http://localhost:9000/runs/1
```

# Docker

## Prerequisites

Have a proper docker install.

## Build docker image

```
mvn clean package docker:build
docker images
```

```

```
