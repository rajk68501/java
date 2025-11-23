# Use a stable OpenJDK version, such as 11
FROM openjdk:26-ea

# Copy the built jar file to the container
COPY target/spring-boot-web.jar /app/spring-boot-web.jar

# Set the working directory
WORKDIR /app

# Run the jar file when the container starts
ENTRYPOINT ["java", "-jar", "spring-boot-web.jar"]
