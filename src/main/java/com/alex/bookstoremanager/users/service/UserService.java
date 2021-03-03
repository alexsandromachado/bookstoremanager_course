package com.alex.bookstoremanager.users.service;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.bookstoremanager.users.dto.MessageDTO;
import com.alex.bookstoremanager.users.dto.UserDTO;
import com.alex.bookstoremanager.users.exception.UserAlreadyExistsException;
import com.alex.bookstoremanager.users.exception.UserNotFoundException;
import com.alex.bookstoremanager.users.mapper.UserMapper;
import com.alex.bookstoremanager.users.repository.UserRepository;


@Service
public class UserService {
	
	public static final UserMapper userMapper = UserMapper.INSTANCE;
	
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public MessageDTO create(UserDTO userToCreateDTO) {
		verifyIfExists(userToCreateDTO.getEmail(), userToCreateDTO.getUsername());
		com.alex.bookstoremanager.users.entity.User userToCreate = userMapper.toModel(userToCreateDTO);
		com.alex.bookstoremanager.users.entity.User createdUser = userRepository.save(userToCreate);
		return creationMessage(createdUser);

	}

	private MessageDTO creationMessage(com.alex.bookstoremanager.users.entity.User createdUser) {
		String createdUserName = createdUser.getName();
		Long createdId = createdUser.getId();
		String createdUserMessage = String.format("User %s with ID %s succesfully created", createdUserName, createdId);
		
		return MessageDTO.builder()
				.message(createdUserMessage)
				.build();
	}
	
	public void delete(Long id) {
		verifyIfExists(id);		
		userRepository.deleteById(id);
		
	}
	
	private void verifyIfExists(String email, String username) {
		Optional<com.alex.bookstoremanager.users.entity.User> foundUser = userRepository.findByEmailOrUsername(email, username);
				if(foundUser.isPresent()) {
					throw new UserAlreadyExistsException(email, username);
				}
	}
	
	private void verifyIfExists(Long id) {
		userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id) );
	}

}
