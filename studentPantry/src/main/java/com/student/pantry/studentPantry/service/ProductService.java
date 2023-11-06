package com.student.pantry.studentPantry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.pantry.studentPantry.builder.ProductBuilder;
import com.student.pantry.studentPantry.entity.Products;
import com.student.pantry.studentPantry.repository.ProductJpa;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductJpa productRepository;

    @Autowired
    public ProductService(ProductJpa productRepository) {
        this.productRepository = productRepository;
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(String productName, int productQuantity, String productExpiryDate) {
        Products product = new ProductBuilder()
            .productName(productName)
            .productQuantity(productQuantity)
            .productExpiryDate(productExpiryDate)
            .build();

        productRepository.save(product);
    }

    public void updateProduct(Long productId, String productName, int productQuantity, String productExpiryDate)
    {
        Optional<Products> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()){
            Products exisitingProducts = productOptional.get();
            exisitingProducts.setProductName(productName);
            exisitingProducts.setProductQuantity(productQuantity);
            exisitingProducts.setProductExpiryDate(productExpiryDate);

            productRepository.save(exisitingProducts);
        }
        
    }

    public void removeProduct(Long productId) 
    {
        productRepository.deleteById(productId);
    }
}