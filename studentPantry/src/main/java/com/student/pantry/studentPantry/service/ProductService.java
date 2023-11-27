package com.student.pantry.studentPantry.service;

import java.util.List;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
    public ProductService(ProductJpa productRepository) {
        this.productRepository = productRepository;
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(String productName, int productQuantity, String productExpiryDate, String productImageURL) {
    	
    	String shortImageURL = shortenUrl(productImageURL);
        Products product = new ProductBuilder()
            .productName(productName)
            .productQuantity(productQuantity)
            .productExpiryDate(productExpiryDate)
            .productImageURL(shortImageURL)
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

    private static final String TINYURL_API_URL = "http://tinyurl.com/api-create.php?url=";

    public String shortenUrl(String longUrl) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(TINYURL_API_URL + longUrl);

            HttpResponse response = httpClient.execute(httpGet);
            
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return responseBody.trim(); 
            } else {
                System.out.println("Failed to shorten URL. HTTP Status Code: " +
                        response.getStatusLine().getStatusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}