package com.student.pantry.studentPantry.service;

public class CartDetails {
    private Long cartProductID;
    private Long userID;
    private Long productID;
    private String productName;
    private int productQuantity;
    private String productImageURL;

    // Default constructor
    public CartDetails() {
    }

    // Constructor with all fields
    public CartDetails(Long cartProductID, Long userID, Long productID, String productName, int productQuantity, String productImageURL) {
        this.cartProductID = cartProductID;
        this.userID = userID;
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productImageURL = productImageURL;
    }

    // Getters and Setters
    public Long getCartProductID() {
        return cartProductID;
    }

    public void setCartProductID(Long cartProductID) {
        this.cartProductID = cartProductID;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    // Optional: toString method for debugging
    @Override
    public String toString() {
        return "CartDetails{" +
                "cartProductID=" + cartProductID +
                ", userID=" + userID +
                ", productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productImageURL='" + productImageURL + '\'' +
                '}';
    }
}
