package com.student.pantry.studentPantry.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.pantry.studentPantry.entity.OrderHistory;



public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>{
    List<OrderHistory> findByUserId(Long userId);
    OrderHistory findByOrderId(Long orderId);
    List<OrderHistory> findByUserIdAndOrderPlacedDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
