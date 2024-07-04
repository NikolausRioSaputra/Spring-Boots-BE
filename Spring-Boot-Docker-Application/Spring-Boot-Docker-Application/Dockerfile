# Use a base image with eclipse-temurin for Java 22
FROM eclipse-temurin:22-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

ARG JAR_FILE=./target/*.jar

# Copy the packaged JAR file into the container
COPY ./target/Spring-Boot-Docker-Application-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will be running on
EXPOSE 8080

# Set the entry point command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]