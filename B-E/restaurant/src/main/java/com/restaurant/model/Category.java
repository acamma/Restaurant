package com.restaurant.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name= "reparti")
public class Category {
	@Id
	@Column(name="idReparto")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer categoryId;
	@Column(name="nome")
	private String name;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public HashMap<String,Object> toMap(){
		HashMap<String,Object> toRet =  new HashMap<String,Object>();
		toRet.put("categoryId", this.getCategoryId());
		toRet.put("name", this.getName());
		return toRet;
	}
}