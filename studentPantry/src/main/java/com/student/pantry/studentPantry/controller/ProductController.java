package com.student.pantry.studentPantry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.pantry.studentPantry.entity.Products;
import com.student.pantry.studentPantry.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{productId}")
    public ResponseEntity<Products> getProductById(@PathVariable Long productId) {
        Products product = productService.getProductById(productId);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<String> addProduct(@RequestParam String productName, @RequestParam int productQuantity, @RequestParam String productExpiryDate, @RequestParam String productImageURL) {
        productService.addProduct(productName, productQuantity, productExpiryDate, productImageURL);
        return ResponseEntity.status(HttpStatus.OK).body("Product added successfully");
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<String> updateProduct(  @RequestParam Long productId, 
                                @RequestParam String productName, 
                                @RequestParam int productQuantity, 
                                @RequestParam String productExpiryDate)
    {
        productService.updateProduct(productId, productName, productQuantity, productExpiryDate);
        return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully...!!!");
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping
    @RequestMapping("/remove")
    public ResponseEntity<String> removeProduct(@RequestParam Long productId)
    {
        productService.removeProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product removed successfully...!!!");
    }
}