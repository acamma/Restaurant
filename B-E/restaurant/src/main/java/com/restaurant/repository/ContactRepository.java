package com.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.restaurant.model.Contact;

public interface ContactRepository extends CrudRepository<Contact,Integer>{
	
}