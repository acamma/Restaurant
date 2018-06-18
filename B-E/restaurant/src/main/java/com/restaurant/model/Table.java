package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name= "tavoli")
public class Table {
	@Id
	@Column(name="idTavolo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer tableId;
	private String username;
	private String password;
	private String online;
	
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId.intValue();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	
	@Override
	public String toString(){
		return String.valueOf(this.getTableId()).concat(" Username:").concat(this.getUsername()).concat(" Online:").concat(this.getOnline());
	}
}
