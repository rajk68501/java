# Use a valid OpenJDK version, like 17 or 20
FROM openjdk:17-jdk-slim

COPY target/myapp.jar /app/myapp.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "myapp.jar"]
