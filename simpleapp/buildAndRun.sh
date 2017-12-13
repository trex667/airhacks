#!/bin/sh
mvn clean package && docker build -t com.airhacks/simpleapp .
docker rm -f simpleapp || true && docker run -d -p 8080:8080 -p 4848:4848 --name simpleapp com.airhacks/simpleapp 
