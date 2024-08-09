#start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

MAINTAINER savindu.me

COPY target/account-management-0.0.1-SNAPSHOT.jar account-management-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/account-management-0.0.1-SNAPSHOT.jar"]