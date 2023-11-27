package com.student.pantry.studentPantry.controller;

import com.student.pantry.studentPantry.entity.ShoppingCart;
import com.student.pantry.studentPantry.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart-view")
public class CartViewController {
    private final CartService cartService;

    @Autowired
    public CartViewController(CartService cartService) {
        this.cartService = cartService;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{userID}")
    public List<ShoppingCart> viewCart(@PathVariable Long userID) {
        return cartService.viewCart(userID);
    }
}
