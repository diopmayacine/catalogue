package com.test.catalogue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
		@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

		private String name;

		public User(String name) {
			this.name = name
		}


		@Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s']",
                id, name);
		}

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}