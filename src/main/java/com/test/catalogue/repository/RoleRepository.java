package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.catalogue.model.AppRole;

public interface RoleRepository  extends JpaRepository<AppRole, Long>{
	public AppRole findByRoleName(String rolename);
}
