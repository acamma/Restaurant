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
import com.restaurant.model.Category;
import com.restaurant.model.Product;
import com.restaurant.model.Table;

@RestController
@RequestMapping("/restaurantServer/table")
@CrossOrigin("http://localhost:4200")
public class TableController extends Restaurant{
	private Logger LOGGER = Logger.getLogger(TableController.class.getName());
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HashMap<String,Object> login(@RequestBody HashMap<String,Object> param) {
		HashMap<String,Object> toRet = new HashMap<String,Object>();
		LOGGER.info("login request for: ".concat(String.valueOf(param.get("username"))));
		Table table = tableRepository.login(String.valueOf(param.get("username")), SHA1(String.valueOf(param.get("password"))));
		tableRepository.setOnline(table.getTableId());
		table.setOnline("s");
		toRet = objToMap(table);
		toRet.remove("password");
		LOGGER.info(String.valueOf(param.get("username")).concat(" logged in..."));
		return toRet;
	}
	
	@RequestMapping(value = "/logout/{id}", method = RequestMethod.GET)
	public int logout(@PathVariable Integer id) {
		LOGGER.info(tableRepository.findById(id).get().getUsername().concat(" logged out"));
		return tableRepository.logout(id);
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public HashMap<Object,Object> menu() {
		LOGGER.info("Retrieving menu...");
		HashMap<Object,Object> menu = new HashMap<Object,Object>();
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<Product> products = new ArrayList<Product>();
		Iterator<Category> it = categoryRepository.findAll().iterator();
		while(it.hasNext()) {
			Category category = it.next();
			categories.add(category);
			products.addAll(productRepository.getProductByCategoryId(category.getCategoryId()));
		}
		menu.put("categories", categories);
		menu.put("products", products);
		return menu;
	}
}
