package com.alex.bookstoremanager.books.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alex.bookstoremanager.books.dto.BookRequestDTO;
import com.alex.bookstoremanager.books.dto.BookResponseDTO;
import com.alex.bookstoremanager.books.service.BookService;
import com.alex.bookstoremanager.users.dto.AuthenticatedUser;

@RestController
@RequestMapping("/api/v1/books")
public class BookController implements BookControllerDocs{
	
private BookService bookService;

@Autowired
public BookController(BookService bookService) {
	this.bookService = bookService;
}

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public BookResponseDTO create(
		@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
		@RequestBody @Valid BookRequestDTO bookRequestDTO) {
	return bookService.create(authenticatedUser, bookRequestDTO);
}

@GetMapping("/{bookId}")
public BookResponseDTO findByIdAndUser(
		@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
		@PathVariable Long bookId) {
	return bookService.findByIdAndUser(authenticatedUser, bookId);
}

@GetMapping
public List<BookResponseDTO> findAllByUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
	return bookService.findAllByUser(authenticatedUser);
}

@DeleteMapping("/{bookId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteByIdAndUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,@PathVariable Long bookId) {
	bookService.deleteByIdAndUser(authenticatedUser, bookId);
}

@PutMapping("/{bookId}")
public BookResponseDTO updateByIdAndUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
		@PathVariable Long bookId,
		@RequestBody @Valid BookRequestDTO bookRequestDTO) {
	return bookService.updateByIdAndUser(authenticatedUser, bookId, bookRequestDTO);
}

}
