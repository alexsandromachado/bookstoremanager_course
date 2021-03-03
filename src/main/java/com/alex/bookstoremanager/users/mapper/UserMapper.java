package com.alex.bookstoremanager.users.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alex.bookstoremanager.users.dto.UserDTO;
import com.alex.bookstoremanager.users.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

User toModel(UserDTO userDTO);

UserDTO toDTO (User user);

}
