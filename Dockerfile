# Use a stable OpenJDK version, such as 11
FROM openjdk:11-jdk-slim

# Copy the built jar file to the container
COPY target/myapp.jar /app/myapp.jar

# Set the working directory
WORKDIR /app

# Run the jar file when the container starts
ENTRYPOINT ["java", "-jar", "myapp.jar"]
