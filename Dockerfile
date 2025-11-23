# Use Eclipse Temurin OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR file into the container
# Replace 'your-app.jar' with your actual jar filename
COPY target/eks.jar .

# Expose the application's port if needed (e.g., 8080)
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "eks.jar"]
