package com.restaurant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.repository.AddressRepository;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.repository.CityRepository;
import com.restaurant.repository.ContactRepository;
import com.restaurant.repository.EmployeeRepository;
import com.restaurant.repository.ProductRepository;
import com.restaurant.repository.TableRepository;

public abstract class Restaurant {
	@Autowired
	protected TableRepository tableRepository;
	@Autowired
	protected CategoryRepository categoryRepository;
	@Autowired
	protected ProductRepository productRepository;
	@Autowired
	protected EmployeeRepository employeeRepository;
	@Autowired
	protected CityRepository cityRepository;
	@Autowired
	protected AddressRepository addressRepository;
	@Autowired
	protected ContactRepository contactRepository;
	
	public HashMap<String,Object> objToMap(Object obj){
		ObjectMapper objMapper = new ObjectMapper();
		HashMap<String,Object> toRet = objMapper.convertValue(obj, new TypeReference<HashMap<String, Object>>() {});
		return toRet;
	}
	
	public String SHA1(String pass) {
		String riga = null;
		try {

			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			byte[] array = sha1.digest(pass.getBytes());
			riga = arrayToString(array);

		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
		}
		return riga;
	}

	private String arrayToString(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i)
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		return sb.toString();
	}
}
