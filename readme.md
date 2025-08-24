# exec commands steps

docker-compose down
docker rmi spring-kotlin-app:latest
./gradlew clean build
docker build -t spring-kotlin-app .
docker-compose up -d