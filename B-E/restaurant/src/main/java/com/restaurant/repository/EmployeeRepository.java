package com.restaurant.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
	final String loginSql = "from dipendenti where username= :username and password= :password";
	final String setOnlineSql = "update dipendenti set online='s' where employeeId= :employeeId";
	final String logoutSql = "update dipendenti set online='n' where employeeId= :employeeId";
	
	@Query(loginSql)
	public Employee login(@Param("username") String username, @Param("password") String password);
	
	@Modifying
	@Transactional
	@Query(setOnlineSql)
	public int setOnline(@Param("employeeId") Integer tableId);
	
	@Modifying
	@Transactional
	@Query("update tavoli set password=:password where employeeId= :employeeId")
	public int setPassword(@Param("employeeId") Integer tableId, @Param("password") String password);
	
	@Modifying
	@Transactional
	@Query(logoutSql)
	public int logout(@Param("employeeId") Integer tableId);
}
