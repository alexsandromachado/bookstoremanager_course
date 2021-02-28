package com.alex.bookstoremanager.author.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alex.bookstoremanager.author.dto.AuthorDTO;
import com.alex.bookstoremanager.author.entity.Author;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author toModel(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}