package com.restaurant.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.model.Table;

public interface TableRepository extends CrudRepository<Table, Integer>{
	final String loginSql = "from tavoli where username= :username and password= :password";
	final String setOnlineSql = "update tavoli set online='s' where tableId= :tableId";
	final String logoutSql = "update tavoli set online='n' where tableId= :tableId";
	
	@Query(loginSql)
	public Table login(@Param("username") String username, @Param("password") String password);
	
	@Modifying
	@Transactional
	@Query(setOnlineSql)
	public int setOnline(@Param("tableId") Integer tableId);
	
	@Modifying
	@Transactional
	@Query("update tavoli set password=:password where tableId= :tableId")
	public int setPassword(@Param("tableId") Integer tableId, @Param("password") String password);
	
	@Modifying
	@Transactional
	@Query(logoutSql)
	public int logout(@Param("tableId") Integer tableId);
}
