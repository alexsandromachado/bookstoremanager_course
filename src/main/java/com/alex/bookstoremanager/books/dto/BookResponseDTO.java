package com.alex.bookstoremanager.books.dto;

import com.alex.bookstoremanager.author.dto.AuthorDTO;
import com.alex.bookstoremanager.publishers.dto.PublisherDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
	
private Long id;
	
	private String name;

	private String isbn;

	private Integer pages;

	private Integer chapters;

    private AuthorDTO author;
   
    private PublisherDTO publisher;

}
