# Dockerfile
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the compiled .jar file into the container1111111111111
COPY target/spring-boot-web.jar /app/my-app.jar

# Expose the application port 8082
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/my-app.jar"]
