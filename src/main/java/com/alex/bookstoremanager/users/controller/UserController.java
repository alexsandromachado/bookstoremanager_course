package com.alex.bookstoremanager.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.bookstoremanager.users.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserControllerDocs {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	

}
