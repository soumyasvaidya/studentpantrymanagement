package com.student.pantry.studentPantry.entity;

import javax.persistence.*;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartProductID;

    private Long userID;

    private Long productID;

    private int productQuantity;

    // Constructors, Getters, and Setters

    public ShoppingCart() {
    }

    public ShoppingCart(Long userID, Long productID, int productQuantity, String status) {
        this.userID = userID;
        this.productID = productID;
        this.productQuantity = productQuantity;
    }

    public Long getCartProductID() {
        return cartProductID;
    }

    public void setCartProductID(Long cartID) {
        this.cartProductID = cartID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
