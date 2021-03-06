package com.alex.bookstoremanager.books.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alex.bookstoremanager.books.dto.BookRequestDTO;
import com.alex.bookstoremanager.books.dto.BookResponseDTO;
import com.alex.bookstoremanager.books.entity.Book;

@Mapper
public interface BookMapper {
	
	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
	
	Book toModel(BookRequestDTO bookRequestDTO);
	
	Book toModel(BookResponseDTO bookResponseDTO);
	
	BookResponseDTO toDTO(Book book);

}
