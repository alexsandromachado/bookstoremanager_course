package com.alex.bookstoremanager.author.exception;

import javax.persistence.EntityNotFoundException;

public class AuthorNotFoudException extends EntityNotFoundException {

	public AuthorNotFoudException(Long id) {
		super(String.format("Author with id %S not exists!", id));
	}

}
