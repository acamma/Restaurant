package com.restaurant.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name= "ingredienti")
public class Ingredients {
	@Id
	@Column(name="idIngrediente")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ingredientId;
	
	@Column(name="descrizione")
	private String description;
	@Column(name="quantita")
	private BigDecimal quantity;

}
