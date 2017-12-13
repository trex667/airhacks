#!/bin/sh
mvn clean package && docker build -t com.airhacks/async .
docker rm -f async || true && docker run -d -p 8080:8080 -p 4848:4848 --name async com.airhacks/async 
