package com.restaurant.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name= "ordini")
public class Orders {
	@Id
	@Column(name="idOrdine")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderId;
	
	@Column(name="idTavolo")
	private Integer tableId;
	@Column(name="idDipendente")
	private Integer employeeId;
	@Column(name="dataOrdine")
	private Date orderDate;
	@Column(name="totale")
	private BigDecimal orderAmmount;
	@Column(name="fattura")
	private String invoice;

}