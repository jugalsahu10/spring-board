# Use the official Gradle image with JDK 17
FROM gradle:8.8-jdk17 AS build

# Set the working directory in the container for the build process
WORKDIR /app

# Copy the Gradle wrapper and other necessary files first to utilize Docker cache efficiently
COPY gradle/ gradle/
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# Copy the entire project source code
COPY src/ src/

# Run the Gradle build (will compile the code, run tests, and create the JAR)
RUN ./gradlew clean build --no-daemon

# Now, use a smaller runtime image to keep the container lightweight
FROM openjdk:17-jdk-slim

# Set the working directory for the runtime container
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=build /app/build/libs/spring-board-0.0.1.jar backend-app.jar

# Install utilities and AWS CLI
RUN apt-get update && \
    apt-get install -y \
    curl \
    unzip \
    less \
    && curl -fSL "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" \
    && unzip awscliv2.zip \
    && ./aws/install \
    && rm -rf awscliv2.zip \
    && rm -rf aws \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Expose the default port for Spring Boot
EXPOSE 8080

# Run the Spring Boot application with embedded Tomcat
CMD ["java", "-jar", "backend-app.jar"]
