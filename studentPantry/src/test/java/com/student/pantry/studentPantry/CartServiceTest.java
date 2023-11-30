package com.student.pantry.studentPantry;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.student.pantry.studentPantry.entity.ShoppingCart;
import com.student.pantry.studentPantry.repository.ShoppingCartRepository;
import com.student.pantry.studentPantry.service.CartService;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    void testAddProductToCart_NewCartItem() {
        // Arrange
        Long userId = 1L;
        Long productId = 100L;
        int quantity = 3;

        when(shoppingCartRepository.findByUserIDAndProductID(anyLong(), anyLong())).thenReturn(null);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        cartService.addProductToCart(userId, productId, quantity);

        // Assert
        verify(shoppingCartRepository, times(1)).findByUserIDAndProductID(userId, productId);
        verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class));
    }

    @Test
    void testAddProductToCart_ExistingCartItem() {
        // Arrange
        Long userId = 1L;
        Long productId = 100L;
        int quantity = 3;

        ShoppingCart existingCartItem = new ShoppingCart();
        existingCartItem.setUserID(userId);
        existingCartItem.setProductID(productId);
        existingCartItem.setProductQuantity(5);

        when(shoppingCartRepository.findByUserIDAndProductID(anyLong(), anyLong())).thenReturn(existingCartItem);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        cartService.addProductToCart(userId, productId, quantity);

        // Assert
        verify(shoppingCartRepository, times(1)).findByUserIDAndProductID(userId, productId);
        verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class));
    }

    @Test
    void testRemoveProductFromCart_ExistingCartItem() {
        // Arrange
        Long userId = 1L;
        Long productId = 100L;
        int quantityToRemove = 2;

        ShoppingCart existingCartItem = new ShoppingCart();
        existingCartItem.setUserID(userId);
        existingCartItem.setProductID(productId);
        existingCartItem.setProductQuantity(5);

        when(shoppingCartRepository.findByUserIDAndProductID(anyLong(), anyLong())).thenReturn(existingCartItem);
       // when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        cartService.removeProductFromCart(userId, productId);

        // Assert
        verify(shoppingCartRepository, times(1)).findByUserIDAndProductID(userId, productId);
       // verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class));
    }

    @Test
    void testRemoveProductFromCart_ExistingCartItem_RemoveEntireItem() {
        // Arrange
        Long userId = 1L;
        Long productId = 100L;
        int quantityToRemove = 5;

        ShoppingCart existingCartItem = new ShoppingCart();
        existingCartItem.setUserID(userId);
        existingCartItem.setProductID(productId);
        existingCartItem.setProductQuantity(5);

        when(shoppingCartRepository.findByUserIDAndProductID(anyLong(), anyLong())).thenReturn(existingCartItem);
        //when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        cartService.removeProductFromCart(userId, productId);

        // Assert
        verify(shoppingCartRepository, times(1)).findByUserIDAndProductID(userId, productId);
        verify(shoppingCartRepository, times(1)).delete(existingCartItem);
    }

    @Test
    void testRemoveProductFromCart_NonExistingCartItem() {
        // Arrange
        Long userId = 1L;
        Long productId = 100L;
        int quantityToRemove = 2;

        when(shoppingCartRepository.findByUserIDAndProductID(anyLong(), anyLong())).thenReturn(null);

        // Act
        cartService.removeProductFromCart(userId, productId);

        // Assert
        verify(shoppingCartRepository, times(1)).findByUserIDAndProductID(userId, productId);
        verify(shoppingCartRepository, never()).save(any(ShoppingCart.class));
        verify(shoppingCartRepository, never()).delete(any(ShoppingCart.class));
    }

    @Test
    void testUpdateProductQuantity_ExistingCartItem() {
        // Arrange
        Long userId = 1L;
        Long productId = 100L;
        int newQuantity = 8;

        ShoppingCart existingCartItem = new ShoppingCart();
        existingCartItem.setUserID(userId);
        existingCartItem.setProductID(productId);
        existingCartItem.setProductQuantity(5);

        when(shoppingCartRepository.findByUserIDAndProductID(anyLong(), anyLong())).thenReturn(existingCartItem);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        cartService.updateProductQuantity(userId, productId, newQuantity);

        // Assert
        verify(shoppingCartRepository, times(1)).findByUserIDAndProductID(userId, productId);
        verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class));
    }

    @Test
    void testUpdateProductQuantity_NonExistingCartItem() {
        // Arrange
        Long userId = 1L;
        Long productId = 100L;
        int newQuantity = 8;

        when(shoppingCartRepository.findByUserIDAndProductID(anyLong(), anyLong())).thenReturn(null);

        // Act
        cartService.updateProductQuantity(userId, productId, newQuantity);

        // Assert
        verify(shoppingCartRepository, times(1)).findByUserIDAndProductID(userId, productId);
        verify(shoppingCartRepository, never()).save(any(ShoppingCart.class));
    }

}

