package com.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.restaurant.model.City;

public interface CityRepository extends CrudRepository<City,Integer> {
	
}
