package com.student.pantry.studentPantry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.pantry.studentPantry.dto.OrderHistoryDto;
import com.student.pantry.studentPantry.response.OrderHistoryResponse;
import com.student.pantry.studentPantry.service.OrderHistoryService;

@RestController
@RequestMapping("/order-history")
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderHistoryDto orderHistoryDTO) {
        // Validate order creation logic and save to database
        OrderHistoryResponse historyResponse = orderHistoryService.createOrder(orderHistoryDTO);
        return new ResponseEntity<>(historyResponse, HttpStatus.CREATED);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderHistoryDto>> getOrderHistoryForUser(@PathVariable Long userId) {
        // Retrieve and return order history for a user
        List<OrderHistoryDto> userOrderHistory = orderHistoryService.getOrderHistoryForUser(userId);
        return new ResponseEntity<>(userOrderHistory, HttpStatus.OK);
    }

    // Other endpoints for updating, deleting, or retrieving order history

    // Exception handling can be added as needed
}

