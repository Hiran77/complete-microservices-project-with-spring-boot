# Dockerfile for card-management

# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add Maintainer Info
MAINTAINER savindu29

# Copy the jar file from the target directory
COPY target/card-management-0.0.1-SNAPSHOT.jar /app/card-management-0.0.1-SNAPSHOT.jar

# Set the working directory
WORKDIR /app

# Execute the jar file
ENTRYPOINT ["java", "-jar", "/app/card-management-0.0.1-SNAPSHOT.jar"]
