FROM ubuntu:latest AS build

VOLUME /tmp

COPY target/todolist-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080