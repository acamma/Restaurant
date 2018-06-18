package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.restaurant.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{
	final String selingr="from prodotti where idReparto= :categoryId";
	
	@Query(selingr)
	public List<Product> getProductByCategoryId(@Param("categoryId") Integer categoryId);
}
