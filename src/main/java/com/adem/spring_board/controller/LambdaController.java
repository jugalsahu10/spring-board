package com.adem.spring_board.controller;

import com.adem.spring_board.service.LambdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LambdaController {

    @Autowired private LambdaService lambdaService;

    @GetMapping("/invoke-lambda")
    public ResponseEntity<String> invokeLambda(@RequestParam(defaultValue = "my-local-lambda") String functionName, @RequestParam(defaultValue = "{\"defaultKey\":\"defaultValue\"}") String payload) {
        String response = lambdaService.invokeLambdaFunction(functionName, payload);
        return ResponseEntity.ok(response);
    }
}
