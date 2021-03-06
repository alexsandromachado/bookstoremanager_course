package com.alex.bookstoremanager.books.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ISBN;

import com.alex.bookstoremanager.author.entity.Author;
import com.alex.bookstoremanager.publishers.entity.Publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

	private Long id;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String name;

	@NotNull
	@ISBN
	private String isbn;

	@NotNull
	@Max(3000)
	private int pages;

	@NotNull
	@Max(100)
	private int chapters;

    @NotNull
    private Long authorId;
    
    @NotNull
    private Long publisherId;
}
