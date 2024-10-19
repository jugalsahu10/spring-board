package com.adem.spring_board.controller;

import com.adem.spring_board.model.Order;
import com.adem.spring_board.model.Product;
import com.adem.spring_board.model.User;
import com.adem.spring_board.repository.OrderRepository;
import com.adem.spring_board.repository.ProductRepository;
import com.adem.spring_board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired private OrderRepository orderRepository;

    @Autowired private ProductRepository productRepository;

    @Autowired private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order, Principal principal) {
        String username = principal.getName();
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

//        Product product = productRepository.findById(order.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));

//        order.setUser(user);
//        order.setProduct(product);
        Order savedOrder = orderRepository.save(order);

        return ResponseEntity.ok(savedOrder);
    }
}


