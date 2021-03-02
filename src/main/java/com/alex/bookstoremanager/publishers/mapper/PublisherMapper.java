package com.alex.bookstoremanager.publishers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alex.bookstoremanager.publishers.dto.PublisherDTO;
import com.alex.bookstoremanager.publishers.entity.Publisher;

@Mapper(componentModel="spring")
public interface PublisherMapper {
	
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    Publisher toModel(PublisherDTO publisherDTO);

    PublisherDTO toDTO(Publisher publisher);

}
