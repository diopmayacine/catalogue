package com.test.catalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.catalogue.exception.ResourceNotFoundException;
import com.test.catalogue.model.AppRole;
import com.test.catalogue.model.AppUser;
import com.test.catalogue.repository.RoleRepository;
import com.test.catalogue.repository.UserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	


	@Override
	public AppUser saveUser(AppUser user) {
		// TODO Auto-generated method stub
		String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword()); 
		user.setPassword(hashedPassword);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		AppRole role = roleRepository.findByRoleName(roleName);
		AppUser user = userRepository.findByUsername(username);
		user.getRoles().add(role);
		userRepository.save(user);
		
	}

	@Override
	public AppUser findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
	
	
}
