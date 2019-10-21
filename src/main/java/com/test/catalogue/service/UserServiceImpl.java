package com.test.catalogue.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.test.catalogue.exception.ResourceNotFoundException;
import com.test.catalogue.model.User;
import com.test.catalogue.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepository = userRepo;
	}
	
	@Override
	public Iterable<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found."));
	}

}