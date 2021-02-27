package com.alex.bookstoremanager.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.bookstoremanager.books.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
