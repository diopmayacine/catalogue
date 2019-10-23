package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.catalogue.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);
}
