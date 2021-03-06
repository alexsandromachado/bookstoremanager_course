package com.alex.bookstoremanager.books.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.bookstoremanager.books.entity.Book;
import com.alex.bookstoremanager.users.entity.User;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	Optional<Book> findByNameAndIsbnAndUser(String name, String isbn, User user);

}
