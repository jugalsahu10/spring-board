package com.adem.spring_board.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${aws.lambda.endpoint-url}")
    private String lambdaEndpointUrl;

    @Value("${aws.access-key-id}")
    private String accessKeyId;

    @Value("${aws.secret-access-key}")
    private String secretAccessKey;

    @Bean
    public AWSLambda awsLambda() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        return AWSLambdaClientBuilder.standard()
                .withEndpointConfiguration(new AWSLambdaClientBuilder.EndpointConfiguration(lambdaEndpointUrl, "us-east-1"))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
