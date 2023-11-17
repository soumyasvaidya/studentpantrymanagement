package com.student.pantry.studentPantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.pantry.studentPantry.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUserIDAndProductID(Long userID, Long productID);
    void deleteByUserID(Long userID);
}
