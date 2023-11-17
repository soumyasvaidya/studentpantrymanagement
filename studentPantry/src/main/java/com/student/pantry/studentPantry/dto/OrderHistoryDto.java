package com.student.pantry.studentPantry.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderHistoryDto {

    private Long orderId;

    private Long userId;

    private Map<Long, Integer> items;

    private LocalDateTime orderPlacedDate;

    // Constructors, getters, setters

    public OrderHistoryDto() {
        // Default constructor
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Long, Integer> items) {
        this.items = items;
    }

    public LocalDateTime getOrderPlacedDate() {
        return orderPlacedDate;
    }

    public void setOrderPlacedDate(LocalDateTime orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate;
    }
}

