# Этап 2: Запуск
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
RUN mkdir -p shared-folder
COPY  ./build/libs/rsi-observer-srv-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
