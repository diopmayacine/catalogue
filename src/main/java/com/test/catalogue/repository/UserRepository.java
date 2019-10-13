package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.catalogue.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
