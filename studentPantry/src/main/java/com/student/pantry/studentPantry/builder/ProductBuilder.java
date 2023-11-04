package com.student.pantry.studentPantry.builder;

import com.student.pantry.studentPantry.entity.Products;

public class ProductBuilder {
	private String productName;
	private int productQuantity;
	private String productExpiryDate;

	public ProductBuilder productName(String productName) {
		this.productName = productName;
		return this;
	}

	public ProductBuilder productQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
		return this;
	}

	public ProductBuilder productExpiryDate(String productExpiryDate) {
		this.productExpiryDate = productExpiryDate;
		return this;
	}

	public Products build() {
		Products product = new Products();
		product.productName = this.productName;
		product.productQuantity = this.productQuantity;
		product.productExpiryDate = this.productExpiryDate;
		return product;
	}
}
