# Build
mvn clean package && docker build -t com.airhacks/fancy .

# RUN

docker rm -f fancy || true && docker run -d -p 8080:8080 -p 4848:4848 --name fancy com.airhacks/fancy 