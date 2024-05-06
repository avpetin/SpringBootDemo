FROM openjdk:17-jdk-slim AS build
EXPOSE 8080
ADD target/springBootDemo-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]