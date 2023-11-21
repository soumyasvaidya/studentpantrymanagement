package com.student.pantry.studentPantry.controller;

import com.student.pantry.studentPantry.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userID}/add/{productID}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long userID, @PathVariable Long productID, @RequestParam int quantity) {
        cartService.addProductToCart(userID, productID, quantity);
        return ResponseEntity.status(HttpStatus.OK).body("Product added in the cart successfully...!!!");
    }

    @PostMapping("/{userID}/remove/{productID}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long userID, @PathVariable Long productID, @RequestParam int quantity) {
        cartService.removeProductFromCart(userID, productID, quantity);
        return ResponseEntity.status(HttpStatus.OK).body("Product removed from the cart successfully...!!!");
    }

    @PostMapping("/{userID}/update/{productID}")
    public ResponseEntity<String> updateProductQuantity(@PathVariable Long userID, @PathVariable Long productID, @RequestParam int newQuantity) {
        cartService.updateProductQuantity(userID, productID, newQuantity);
        return ResponseEntity.status(HttpStatus.OK).body("Product updated in the cart successfully...!!!");
    }
}
