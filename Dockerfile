FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

EXPOSE 8080

COPY target/todolist-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]