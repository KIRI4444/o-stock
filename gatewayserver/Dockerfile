FROM openjdk:17-slim

LABEL maintainer="Kirill"

WORKDIR /app
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]