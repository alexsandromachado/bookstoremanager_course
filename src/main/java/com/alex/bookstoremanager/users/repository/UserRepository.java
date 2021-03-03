package com.alex.bookstoremanager.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.bookstoremanager.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmailOrUsername(String email, String username);
	
    Optional<User> findByUsername(String username);

}
