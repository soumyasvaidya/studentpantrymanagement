package com.student.pantry.studentPantry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.pantry.studentPantry.entity.Products;
import com.student.pantry.studentPantry.entity.ShoppingCart;
import com.student.pantry.studentPantry.repository.ProductJpa;
import com.student.pantry.studentPantry.repository.ShoppingCartRepository;

@Service
public class CartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;
    private final ProductJpa productJpa;

    @Autowired
    public CartService(ShoppingCartRepository shoppingCartRepository,ProductJpa productJpa) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productJpa=productJpa;
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

         Optional<Products> product=productJpa.findById(productID);
            System.out.println("prduct+" + product.toString());
            if(product.isPresent()){
                int oldQuantity=product.get().getProductQuantity();
                Products newProduct=product.get();
                newProduct.setProductQuantity(oldQuantity-quantity);
                System.out.println("prduct+" + newProduct.toString());
                 productJpa.save(newProduct);
            }
            
    }

    public void removeProductFromCart(Long userID, Long productID) {
        ShoppingCart cartItem = shoppingCartRepository.findByUserIDAndProductID(userID, productID);

        if (cartItem != null) {
            // Update quantity or remove the item if quantity becomes zero
            int newQuantity = cartItem.getProductQuantity();
            if (newQuantity > 0) {
               shoppingCartRepository.delete(cartItem);  
               Optional<Products> product=productJpa.findById(productID);
                System.out.println("prduct+" + product.toString());
                if(product.isPresent()){
                    int oldQuantity=product.get().getProductQuantity();
                    Products newProduct=product.get();
                    newProduct.setProductQuantity(oldQuantity+newQuantity);
                    System.out.println("prduct+" + newProduct.toString());
                    productJpa.save(newProduct);
                }
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
