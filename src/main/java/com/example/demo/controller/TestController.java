package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.LambdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired private UserRepository userRepository;
    @Autowired private LambdaService lambdaService;

    @GetMapping("/test")
    public ResponseEntity getTest() {
        return ResponseEntity.of(Optional.of(userRepository.findAll(PageRequest.of(0, 10))));
    }

    @GetMapping("/invoke-lambda")
    public ResponseEntity invokeLambda( @RequestParam(defaultValue = "my-local-lambda") String functionName,
                                @RequestParam(defaultValue = "{\"defaultKey\":\"defaultValue\"}") String payload) {
        return ResponseEntity.of(Optional.of(lambdaService.invokeLambdaFunction(functionName, payload)));
    }
}
