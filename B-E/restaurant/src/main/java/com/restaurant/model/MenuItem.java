package com.restaurant.model;

import java.util.List;
import com.restaurant.model.Product;
import com.restaurant.model.Category;

public class MenuItem {
	
	private Category category;
	private List<Product> products;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}