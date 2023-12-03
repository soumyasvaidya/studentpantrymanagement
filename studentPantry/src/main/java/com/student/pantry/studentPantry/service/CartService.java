package com.student.pantry.studentPantry.service;

import com.student.pantry.studentPantry.entity.Products;
import com.student.pantry.studentPantry.entity.ShoppingCart;
import com.student.pantry.studentPantry.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    public CartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addProductToCart(Long userID, Long productID, int quantity) {
        ShoppingCart cartItem = shoppingCartRepository.findByUserIDAndProductID(userID, productID);

        if (cartItem == null) {
            cartItem = new ShoppingCart();
            cartItem.setUserID(userID);
            cartItem.setProductID(productID);
            cartItem.setProductQuantity(quantity);
        } else {
            // Update quantity if the item already exists in the cart
            cartItem.setProductQuantity(cartItem.getProductQuantity() + quantity);
        }

        shoppingCartRepository.save(cartItem);
    }

    public void removeProductFromCart(Long userID, Long productID) {
        ShoppingCart cartItem = shoppingCartRepository.findByUserIDAndProductID(userID, productID);

        if (cartItem != null) {
            // Update quantity or remove the item if quantity becomes zero
            int newQuantity = cartItem.getProductQuantity();
            if (newQuantity > 0) {
               shoppingCartRepository.delete(cartItem);  
            } 
        }
    }

    public void updateProductQuantity(Long userID, Long productID, int newQuantity) {
        ShoppingCart cartItem = shoppingCartRepository.findByUserIDAndProductID(userID, productID);

        if (cartItem != null) {
            cartItem.setProductQuantity(newQuantity);
            shoppingCartRepository.save(cartItem);
        }
    }

    public List<CartDetails> viewCart(Long userID) {
        List<ShoppingCart> cartItems = shoppingCartRepository.findByUserID(userID);
        List<CartDetails> cartDetailsList = new ArrayList<>();

        for (ShoppingCart item : cartItems) {
            Products product = productService.getProductById(item.getProductID());
            if (product != null) {
                CartDetails details = new CartDetails();
                details.setCartProductID(item.getCartProductID());
                details.setUserID(item.getUserID());
                details.setProductID(item.getProductID());
                details.setProductName(product.getProductName());
                details.setProductQuantity(item.getProductQuantity());
                details.setProductImageURL(product.getProductImageURL());
                cartDetailsList.add(details);
            }
        }

        return cartDetailsList;
    }
}
