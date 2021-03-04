package com.alex.bookstoremanager.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alex.bookstoremanager.users.dto.AuthenticatedUser;
import com.alex.bookstoremanager.users.entity.User;
import com.alex.bookstoremanager.users.exception.UserNotFoundException;
import com.alex.bookstoremanager.users.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
		.orElseThrow(() -> new UserNotFoundException(String.format("User not found with username %s", username)));
		return new AuthenticatedUser(
				user.getUsername(),
				user.getPassword(),
				user.getRole().getDescription()
				);
	}

}
