package com.student.pantry.studentPantry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.student.pantry.studentPantry.entity.Products;
import com.student.pantry.studentPantry.repository.ProductJpa;
import com.student.pantry.studentPantry.service.EmailService;
import com.student.pantry.studentPantry.service.ProductService;
import com.student.pantry.studentPantry.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductJpa productJpa;

    @Mock
    private EmailService emailService;

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        // Arrange
        List<Products> expectedProducts = Collections.singletonList(new Products());
        when(productJpa.findAll()).thenReturn(expectedProducts);

        // Act
        List<Products> result = productService.getAllProducts();

        // Assert
        assertEquals(expectedProducts, result);
    }

    @Test
    void testAddProduct() {
        // Arrange
        String productName = "TestProduct";
        int productQuantity = 10;
        String productExpiryDate = "2023-12-31";

        when(userServiceImpl.getAllUserEmails()).thenReturn(null);
        // Act
        productService.addProduct(productName, productQuantity, productExpiryDate,"url");

        // Assert
        verify(productJpa, times(1)).save(any(Products.class));
        verify(userServiceImpl, times(1)).getAllUserEmails();
        verify(emailService, times(0)).sendOrderUpdates(anyString());
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Long productId = 1L;
        String productName = "UpdatedProduct";
        int productQuantity = 20;
        String productExpiryDate = "2024-12-31";

        Products existingProduct = new Products();
        existingProduct.setProductId(productId);

        when(productJpa.findById(productId)).thenReturn(Optional.of(existingProduct));

        // Act
        productService.updateProduct(productId, productName, productQuantity, productExpiryDate);

        // Assert
        verify(productJpa, times(1)).findById(productId);
        verify(productJpa, times(1)).save(existingProduct);
        verify(userServiceImpl, times(1)).getAllUserEmails();
        verify(emailService, times(0)).sendOrderUpdates(anyString());
    }

    @Test
    void testRemoveProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.removeProduct(productId);

        // Assert
        verify(productJpa, times(1)).deleteById(productId);
    }

    // Add more test cases as needed...

}
