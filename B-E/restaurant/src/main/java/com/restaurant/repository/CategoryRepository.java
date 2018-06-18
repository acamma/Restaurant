package com.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.restaurant.model.Category;

public interface CategoryRepository extends CrudRepository<Category,Integer>{
	
}