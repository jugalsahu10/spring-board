# Getting Started

## Description
### `docker-compose.yaml`

- **localstack**: Provides local emulation of AWS services (Lambda, EC2, API Gateway, RDS). It maps necessary ports and uses volumes to persist data and share Docker socket for Docker operations.

- **mysql**: Runs a MySQL 8.0 database with the root password and user credentials set via environment variables. It maps port 3307 on the host to port 3306 in the container.

- **backend-app**: The Spring Boot application that connects to the MySQL database and communicates with LocalStack's Lambda service. It depends on both the MySQL and LocalStack services.

- **init-lambda**: Initializes a Lambda function in LocalStack using AWS CLI. It copies the Lambda function zip file into the container and creates the function using LocalStack’s endpoint.

### `Dockerfile`

- **Base Image**: Uses `openjdk:17-jdk-slim` to run a Java application.
- **Setup**: Copies the JAR file into the container, installs necessary utilities and AWS CLI.
- **Run**: Executes the Spring Boot application using `java -jar`.

### `init-lambda/Dockerfile`

- **Base Image**: Uses `amazon/aws-cli` to run AWS CLI commands.
- **Setup**: Copies the Lambda function zip file into the container.
- **Entry Point**: Configures AWS CLI and creates a Lambda function in LocalStack using the provided zip file and endpoint.

This setup provides a complete local development environment with a simulated AWS environment, a MySQL database, and a Spring Boot application interacting with both.
