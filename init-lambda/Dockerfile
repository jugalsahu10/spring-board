# Dockerfile
FROM amazon/aws-cli

# Copy the lambda function zip file into the image
COPY ../aws/lambda/lambda_function.zip /lambda/lambda_function.zip

# Set the entrypoint for the container
ENTRYPOINT ["/bin/sh", "-c", "\
    aws configure set aws_access_key_id test && \
    aws configure set aws_secret_access_key test && \
    aws configure set region us-east-1 && \
    aws configure set output json && \
    aws lambda create-function --function-name my-local-lambda --runtime python3.8 --zip-file fileb:///lambda/lambda_function.zip --handler lambda_function.lambda_handler --role arn:aws:iam::000000000000:role/irrelevant --endpoint-url=http://localstack:4566"]