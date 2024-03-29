package com.test.catalogue.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1047439121714896976L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String description;
	Double price;
	boolean selected;
	boolean avalaible;
	String pictureUrl;
	@ManyToOne
	Category category;

}
