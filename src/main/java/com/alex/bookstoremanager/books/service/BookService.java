package com.alex.bookstoremanager.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.bookstoremanager.author.entity.Author;
import com.alex.bookstoremanager.author.service.AuthorService;
import com.alex.bookstoremanager.books.dto.BookRequestDTO;
import com.alex.bookstoremanager.books.dto.BookResponseDTO;
import com.alex.bookstoremanager.books.entity.Book;
import com.alex.bookstoremanager.books.exception.BookAlreadyExistsException;
import com.alex.bookstoremanager.books.exception.BookNotFoundExeption;
import com.alex.bookstoremanager.books.mapper.BookMapper;
import com.alex.bookstoremanager.books.repository.BookRepository;
import com.alex.bookstoremanager.publishers.entity.Publisher;
import com.alex.bookstoremanager.publishers.service.PublisherService;
import com.alex.bookstoremanager.users.dto.AuthenticatedUser;
import com.alex.bookstoremanager.users.entity.User;
import com.alex.bookstoremanager.users.service.UserService;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

	private final BookMapper bookMapper = BookMapper.INSTANCE;
	
	private BookRepository bookRepository;
	
	private AuthorService authorService;
	
	private UserService userService;
	
	private PublisherService publisherService;
	
	public BookResponseDTO create(AuthenticatedUser authenticatedUser, BookRequestDTO bookRequestDTO) {
		User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
		verifyIfBookIsAlreadyRegistered(foundAuthenticatedUser, bookRequestDTO);	
		
		Author foundAuthor = authorService.verifyAndGetIfExists(bookRequestDTO.getAuthorId());
		Publisher foundPublisher = publisherService.verifyAndGetIfExists(bookRequestDTO.getPublisherId());
		
		Book bookToSave = bookMapper.toModel(bookRequestDTO);
		bookToSave.setUser(foundAuthenticatedUser);
		bookToSave.setAuthor(foundAuthor);
		bookToSave.setPublisher(foundPublisher);
		Book savedBook = bookRepository.save(bookToSave);
		
		return bookMapper.toDTO(savedBook);
		
		
	}
	
	public BookResponseDTO findByIdAndUser(AuthenticatedUser authenticatedUser, Long bookId) {
		User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
		return bookRepository.findByIdAndUser(bookId, foundAuthenticatedUser)
				.map(bookMapper::toDTO)
				.orElseThrow(() -> new BookNotFoundExeption(bookId));
		
	}
	
	public List<BookResponseDTO> findAllByUser(AuthenticatedUser authenticatedUser){
		User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
		return bookRepository.findAllByUser(foundAuthenticatedUser)
		.stream()
		.map(bookMapper::toDTO)
		.collect(Collectors.toList());
	}

	private void verifyIfBookIsAlreadyRegistered(User foundUser, BookRequestDTO bookRequestDTO) {
		bookRepository.findByNameAndIsbnAndUser(bookRequestDTO.getName(), bookRequestDTO.getIsbn(), foundUser)
			.ifPresent(duplicatedBook -> {
				throw new BookAlreadyExistsException(bookRequestDTO.getName(), bookRequestDTO.getIsbn(), foundUser.getUsername());
			});
	}
	
}
