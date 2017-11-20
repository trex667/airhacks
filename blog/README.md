# Build
mvn clean package && docker build -t com.airhacks/blog .

# RUN

docker rm -f blog || true && docker run -d -p 8080:8080 -p 4848:4848 --name blog com.airhacks/blog 