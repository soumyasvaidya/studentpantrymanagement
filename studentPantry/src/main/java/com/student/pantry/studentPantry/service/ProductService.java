package com.student.pantry.studentPantry.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.pantry.studentPantry.builder.ProductBuilder;
import com.student.pantry.studentPantry.entity.Products;
import com.student.pantry.studentPantry.repository.ProductJpa;

@Service
public class ProductService {
    private final ProductJpa productRepository;
    List<String> allUserEmail = null;
    
    @Autowired
    UserServiceImpl userServiceImpl;
    
    @Autowired
    EmailService emailService;

    @Autowired
    public ProductService(ProductJpa productRepository,UserServiceImpl userServiceImpl) {
        this.productRepository = productRepository;
        this.userServiceImpl=userServiceImpl;
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
        allUserEmail = userServiceImpl.getAllUserEmails();
        System.out.println(allUserEmail);
        if(allUserEmail!=null && !allUserEmail.isEmpty()) {
        	for (String email : allUserEmail) {
        		System.out.println(email);
        		emailService.sendOrderUpdates(email);
        	}
       	 
        }
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
            allUserEmail = userServiceImpl.getAllUserEmails();
            System.out.println(allUserEmail);
            if(allUserEmail!=null && !allUserEmail.isEmpty()) {
            	for (String email : allUserEmail) {
            		System.out.println(email);
            		emailService.sendOrderUpdates(email);
            	}
            }
        }
        
    }

    public void removeProduct(Long productId) 
    {
        productRepository.deleteById(productId);
    }
}