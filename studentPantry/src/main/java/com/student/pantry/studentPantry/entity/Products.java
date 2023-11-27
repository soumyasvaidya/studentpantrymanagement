package com.student.pantry.studentPantry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PantryProduct")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long productId;
    public String productName;
    public int productQuantity;
    public String productExpiryDate;
	public String productImageURL;
    
    public Products() {
    }

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductExpiryDate() {
		return productExpiryDate;
	}

	public void setProductExpiryDate(String productExpiryDate) {
		this.productExpiryDate = productExpiryDate;
	}
	
	public String getproductImageURL() {
		return productImageURL;
	}

	public void setProductImageURL(String productImageURL) {
		this.productImageURL = productImageURL;
	}
}
