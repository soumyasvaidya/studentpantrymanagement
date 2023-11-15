package com.student.pantry.studentPantry.repository;

import com.student.pantry.studentPantry.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUserIDAndProductID(Long userID, Long productID);
}
