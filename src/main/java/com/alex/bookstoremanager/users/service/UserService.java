package com.alex.bookstoremanager.users.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.bookstoremanager.users.dto.MessageDTO;
import com.alex.bookstoremanager.users.dto.UserDTO;
import com.alex.bookstoremanager.users.exception.UserAlreadyExistsException;
import com.alex.bookstoremanager.users.exception.UserNotFoundException;
import com.alex.bookstoremanager.users.mapper.UserMapper;
import com.alex.bookstoremanager.users.repository.UserRepository;


@Service
public class UserService { private final static UserMapper userMapper = UserMapper.INSTANCE;

private UserRepository userRepository;

@Autowired
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
   
}

public MessageDTO create(UserDTO userToCreateDTO) {
    verifyIfExists(userToCreateDTO.getEmail(), userToCreateDTO.getUsername());
    com.alex.bookstoremanager.users.entity.User userToCreate = userMapper.toModel(userToCreateDTO);
  //  userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));

    com.alex.bookstoremanager.users.entity.User createdUser = userRepository.save(userToCreate);
    return com.alex.bookstoremanager.users.utils.MessageDTOUtils.creationMessage(createdUser);
}

public MessageDTO update(Long id, UserDTO userToUpdateDTO) {
    com.alex.bookstoremanager.users.entity.User foundUser = verifyAndGetIfExists(id);

    userToUpdateDTO.setId(foundUser.getId());
    com.alex.bookstoremanager.users.entity.User userToUpdate = userMapper.toModel(userToUpdateDTO);
   // userToUpdate.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
    userToUpdate.setCreatedDate(foundUser.getCreatedDate());

    com.alex.bookstoremanager.users.entity.User updatedUser = userRepository.save(userToUpdate);
    return com.alex.bookstoremanager.users.utils.MessageDTOUtils.updatedMessage(updatedUser);
}


public void delete(Long id) {
    verifyAndGetIfExists(id);
    userRepository.deleteById(id);
}

private com.alex.bookstoremanager.users.entity.User verifyAndGetIfExists(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
}

private void verifyIfExists(String email, String username) {
    Optional<com.alex.bookstoremanager.users.entity.User> foundUser = userRepository.findByEmailOrUsername(email, username);
    if (foundUser.isPresent()) {
        throw new UserAlreadyExistsException(email, username);
    }
}

public com.alex.bookstoremanager.users.entity.User verifyAndGetUserIfExists(String username) {
    return userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException(username));
}
}