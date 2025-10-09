FROM openjdk:17-slim

LABEL maintainer="Kirill"

WORKDIR /app

# Копируем конкретный JAR файл
COPY target/spring-boot-starter-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]