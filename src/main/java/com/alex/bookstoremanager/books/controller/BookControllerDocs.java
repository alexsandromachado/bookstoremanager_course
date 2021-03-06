package com.alex.bookstoremanager.books.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import com.alex.bookstoremanager.books.dto.BookRequestDTO;
import com.alex.bookstoremanager.books.dto.BookResponseDTO;
import com.alex.bookstoremanager.users.dto.AuthenticatedUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Books module Management")
public interface BookControllerDocs {
	
	@ApiOperation(value = "Book creation operation")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success book creation"),
			@ApiResponse(code = 400, message = "Missing required field, wrong field range value or book already registered on system")
	})
	BookResponseDTO create(AuthenticatedUser authenticatedUser, BookRequestDTO bookRequestDTO);

}
