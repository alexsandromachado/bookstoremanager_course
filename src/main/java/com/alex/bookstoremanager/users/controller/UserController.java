package com.alex.bookstoremanager.users.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alex.bookstoremanager.users.dto.MessageDTO;
import com.alex.bookstoremanager.users.dto.UserDTO;
import com.alex.bookstoremanager.users.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserControllerDocs {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageDTO create(@RequestBody @Valid UserDTO userToCreateDTO) {
		return userService.create(userToCreateDTO);
	}

}
