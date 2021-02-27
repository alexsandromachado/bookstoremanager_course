package com.alex.bookstoremanager.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.bookstoremanager.author.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
