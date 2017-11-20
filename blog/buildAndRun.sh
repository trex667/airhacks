#!/bin/sh
mvn clean package && docker build -t com.airhacks/blog .
docker rm -f blog || true && docker run -d -e "maxPoolSize=12" -p 8080:8080 --name blog com.airhacks/blog 
