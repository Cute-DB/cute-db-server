[![Build Status](https://travis-ci.org/Cute-DB/cute-db-server.svg?branch=master)](https://travis-ci.org/Cute-DB/cute-db-server) 

[![Dependency Status](https://www.versioneye.com/user/projects/5760a1654931050036a5e9c1/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5760a1654931050036a5e9c1)

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
