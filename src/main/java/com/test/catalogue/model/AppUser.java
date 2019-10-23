package com.test.catalogue.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class AppUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4446969537082545787L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();

}