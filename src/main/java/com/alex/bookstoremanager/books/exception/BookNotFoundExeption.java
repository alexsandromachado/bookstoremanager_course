package com.alex.bookstoremanager.books.exception;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundExeption extends EntityNotFoundException{

	public BookNotFoundExeption(Long bookId) {
		
		super(String.format("Book with id %s not exists!", bookId));
		
	}
	
	

}
