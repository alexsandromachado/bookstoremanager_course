package com.alex.bookstoremanager.books.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

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
		
	@ApiOperation(value = "Book find by id and user operation")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success book found"),
			@ApiResponse(code = 404, message = "Book not found error")
	})
	BookResponseDTO findByIdAndUser(AuthenticatedUser authenticatedUser, Long bookId);
	
	@ApiOperation(value = "List all books by a specific authenticated user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Book list found by authenticated user informed")
	})
	List<BookResponseDTO> findAllByUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser);

}
