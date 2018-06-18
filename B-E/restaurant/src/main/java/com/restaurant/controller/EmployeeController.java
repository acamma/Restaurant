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
@RequestMapping("/restaurantServer/employee")
@CrossOrigin("http://localhost:4200")
public class EmployeeController extends Restaurant{
	
	private Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HashMap<String,Object> login(@RequestBody HashMap<String,Object> param) {
		LOGGER.info("login request for: ".concat(String.valueOf(param.get("username"))));
		Employee eployee = employeeRepository.login(String.valueOf(param.get("username")), SHA1(String.valueOf(param.get("password"))));
		employeeRepository.setOnline(eployee.getEmployeeId());
		eployee.setOnline("s");
		
		Address address = addressRepository.findById(eployee.getAddressId()).get();
		String city = cityRepository.findById(address.getCityId()).get().getDenomination();
		String completeAddress = address.getStreet().concat(" ").concat(address.getNumber()).concat(", ").concat(address.getPostalCode()).concat(" ").concat(city);
		Contact contact = contactRepository.findById(eployee.getContactId()).get();
		
		HashMap<String,Object> toRet =  new HashMap<String,Object>();
		toRet.put("employeeId", eployee.getEmployeeId());
		toRet.put("name", eployee.getName());
		toRet.put("lastname", eployee.getLastName());
		toRet.put("birthDate", eployee.getBirthDate());
		toRet.put("fiscalCode", eployee.getFiscalCode());
		toRet.put("address", completeAddress);
		toRet.put("contact", contact);
		LOGGER.info(String.valueOf(param.get("username")).concat(" logged in..."));
		return toRet;
	}
	
	@RequestMapping(value = "/logout/{id}", method = RequestMethod.GET)
	public int logout(@PathVariable Integer id) {
		LOGGER.info(employeeRepository.findById(id).get().getUsername().concat(" logged out"));
		return employeeRepository.logout(id);
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public HashMap<Object,Object> menu() {
		LOGGER.info("Retrieving menu...");
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
		LOGGER.info("Menu: ".concat(menu.toString()));
		return menu;
	}

}
