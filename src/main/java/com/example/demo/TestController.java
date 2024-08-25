package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("")
    public ResponseEntity<Map<String, String>> getTest() {
        return ResponseEntity.ok(new HashMap<>());
    }
}
