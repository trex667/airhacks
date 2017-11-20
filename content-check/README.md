# Build
mvn clean package && docker build -t com.airhacks/uri-check .

# RUN

docker rm -f uri-check || true && docker run -d -p 8080:8080 -p 4848:4848 --name uri-check com.airhacks/uri-check 