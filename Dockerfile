# Use an official OpenJDK image as a base
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled .jar file into the container
COPY target/my-app.jar /app/my-app.jar

# Expose the application port
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "my-app.jar"]
