#start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add Maintainer Info
MAINTAINER savindu29

# Add a volume pointing to /tmp
COPY target/loan-management-0.0.1-SNAPSHOT.jar loan-management-0.0.1-SNAPSHOT.jar

# execute the jar file
ENTRYPOINT ["java", "-jar", "/loan-management-0.0.1-SNAPSHOT.jar"]