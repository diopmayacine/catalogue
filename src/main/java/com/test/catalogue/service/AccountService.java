package com.test.catalogue.service;

import com.test.catalogue.model.AppRole;
import com.test.catalogue.model.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username, String roleName);
	public AppUser findUserByUsername(String username);
	
}
