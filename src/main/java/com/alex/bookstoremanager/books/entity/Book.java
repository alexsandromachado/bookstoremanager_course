package com.alex.bookstoremanager.books.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import com.alex.bookstoremanager.author.entity.Author;
import com.alex.bookstoremanager.publishers.entity.Publisher;
import com.alex.bookstoremanager.users.entity.User;

import lombok.Data;

@Data
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false)
	private String isbn;

	@Column(columnDefinition = "integer default 0")
	private int pages;

    @Column(columnDefinition = "integer default 0")
	private int chapters;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Author author;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Publisher publisher;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private User user;

}
