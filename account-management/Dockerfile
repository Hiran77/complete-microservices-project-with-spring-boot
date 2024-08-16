# Dockerfile for account-management

# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add Maintainer Info
MAINTAINER savindu29

# Copy the jar file from the target directory
COPY target/account-management-0.0.1-SNAPSHOT.jar /app/account-management-0.0.1-SNAPSHOT.jar

# Set the working directory
WORKDIR /app

# Execute the jar file
ENTRYPOINT ["java", "-jar", "/app/account-management-0.0.1-SNAPSHOT.jar"]
