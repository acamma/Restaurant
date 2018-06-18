package com.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.restaurant.model.Address;

public interface AddressRepository extends CrudRepository<Address,Integer>{
	
}