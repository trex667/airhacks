#!/bin/sh
mvn clean package && docker build -t airhacks/blog .
docker rm -f blog || true && docker run -d -p 8080:8080 --name blog airhacks/blog 
