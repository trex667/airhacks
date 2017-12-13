# Build
mvn clean package && docker build -t com.airhacks/simpleapp-tt .

# RUN

docker rm -f simpleapp-tt || true && docker run -d -p 8080:8080 -p 4848:4848 --name simpleapp-tt com.airhacks/simpleapp-tt 