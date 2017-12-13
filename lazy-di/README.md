# Build
mvn clean package && docker build -t com.airhacks/lazy-di .

# RUN

docker rm -f lazy-di || true && docker run -d -p 8080:8080 -p 4848:4848 --name lazy-di com.airhacks/lazy-di 