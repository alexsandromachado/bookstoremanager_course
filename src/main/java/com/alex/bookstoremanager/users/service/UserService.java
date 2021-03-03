package com.alex.bookstoremanager.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.bookstoremanager.users.mapper.UserMapper;
import com.alex.bookstoremanager.users.repository.UserRepository;

@Service
public class UserService {
	
	public static final UserMapper userMapper = UserMapper.INSTANCE;
	
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	

}
