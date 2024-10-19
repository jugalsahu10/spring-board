package com.adem.spring_board.service;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LambdaService {

    @Autowired
    private AWSLambda awsLambda;

    public String invokeLambdaFunction(String functionName, String payload) {
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(functionName)
                .withPayload(payload);

        InvokeResult invokeResult = awsLambda.invoke(invokeRequest);
        return new String(invokeResult.getPayload().array());
    }
}
