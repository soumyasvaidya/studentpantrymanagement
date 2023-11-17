package com.student.pantry.studentPantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.pantry.studentPantry.entity.ShoppingCart;
import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUserIDAndProductID(Long userID, Long productID);
    void deleteByUserID(Long userID);
    List<ShoppingCart> findByUserID(Long userID);
}
