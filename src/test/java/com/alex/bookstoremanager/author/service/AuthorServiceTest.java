package com.alex.bookstoremanager.author.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alex.bookstoremanager.author.builder.AuthorDTOBuilder;
import com.alex.bookstoremanager.author.mapper.AuthorMapper;
import com.alex.bookstoremanager.author.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
	
	private final AuthorMapper authorMapper = AuthorMapper.INSTANCE; 
	
	@Mock
	private AuthorRepository authorReposiory;

	@InjectMocks
	private AuthorService authorService;
	
	private AuthorDTOBuilder authorDTOBuilder;
	
	@BeforeEach
	void setUp() {
		authorDTOBuilder = AuthorDTOBuilder.builder().build();
	}
	
}
