package com.alex.bookstoremanager.users.controller;

import org.springframework.web.bind.annotation.PathVariable;

import com.alex.bookstoremanager.users.dto.MessageDTO;
import com.alex.bookstoremanager.users.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("System users management")
public interface UserControllerDocs {
	
	@ApiOperation(value = "User creation operation")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success user creation"),
			@ApiResponse(code = 400, message = "Missing required field, or an error on validation field rules")
	})
	 MessageDTO create(UserDTO userToCreateDTO);
	
	@ApiOperation(value = "User exclusion operation")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Success user exclusion"),
			@ApiResponse(code = 404, message = "User with informed id not found in the system")
	})
	public void delete(Long id);

}
