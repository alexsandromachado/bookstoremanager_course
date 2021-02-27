package com.alex.bookstoremanager.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.bookstoremanager.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
