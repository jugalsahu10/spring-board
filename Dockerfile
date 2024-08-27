# Use an appropriate base image for your backend application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/demo-0.0.3-SNAPSHOT.jar /app/backend-app.jar

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

# Run the application
CMD ["java", "-jar", "backend-app.jar"]
