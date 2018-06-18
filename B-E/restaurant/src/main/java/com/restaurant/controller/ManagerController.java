package com.restaurant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.Restaurant;
import com.restaurant.model.Address;
import com.restaurant.model.Category;
import com.restaurant.model.Contact;
import com.restaurant.model.Employee;
import com.restaurant.model.Product;

@RestController
@RequestMapping("/restaurantServer/manager")
@CrossOrigin("http://localhost:4200")
public class ManagerController extends Restaurant{
	
	private Logger LOGGER = Logger.getLogger(ManagerController.class.getName());
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public HashMap<Object,Object> menu() {
		HashMap<Object,Object> menu = new HashMap<Object,Object>();
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<HashMap<String,Object>> productsMaps = new ArrayList<HashMap<String,Object>>();
		Iterator<Category> it = categoryRepository.findAll().iterator();
		while(it.hasNext()) {
			Category category = it.next();
			categories.add(category);
			products.clear();
			products.addAll(productRepository.getProductByCategoryId(category.getCategoryId()));
			Iterator<Product> itprod = products.iterator();
			while(itprod.hasNext()) {
				HashMap<String,Object> prod = objToMap(itprod.next());
				prod.remove("password");
				productsMaps.add(prod);
			}
		}
		menu.put("categories", categories);
		menu.put("products",productsMaps);
		return menu;
	}

}