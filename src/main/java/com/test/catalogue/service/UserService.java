package com.test.catalogue.service;

import com.test.catalogue.model.User;

public interface UserService {
	Iterable<User> getAllUsers();
	User getUser(long id);
}
