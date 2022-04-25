# Start with a base image containing Java runtime
FROM maven:3.8.5-openjdk-8-slim

MAINTAINER liuxinpeng "kscbxxLiuXP@126.com"

# Add project source code
COPY src/ /app/src
COPY pom.xml /app
RUN mkdir /app/upload

# Add maven repository to accelerate building
COPY settings.xml /usr/share/maven/conf/

# Working dir is now the project dir
WORKDIR /app

# Create jar file
RUN mvn clean package -Dmaven.test.skip=true


FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# Run the jar file
ENTRYPOINT ["java","-jar","/app/target/LoongsonVisualDebug-1.0-SNAPSHOT.jar","upload=/app/upload"]