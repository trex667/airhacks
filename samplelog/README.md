# Build
mvn clean package && docker build -t com.airhacks/samplelog .

# RUN

docker rm -f samplelog || true && docker run -d -p 8080:8080 -p 4848:4848 --name samplelog com.airhacks/samplelog 