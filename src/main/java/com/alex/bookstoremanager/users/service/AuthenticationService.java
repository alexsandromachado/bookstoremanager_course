package com.alex.bookstoremanager.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alex.bookstoremanager.users.dto.AuthenticatedUser;
import com.alex.bookstoremanager.users.dto.JwtRequest;
import com.alex.bookstoremanager.users.dto.JwtResponse;
import com.alex.bookstoremanager.users.entity.User;
import com.alex.bookstoremanager.users.exception.UserNotFoundException;
import com.alex.bookstoremanager.users.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	public JwtResponse createAuthenticationToken(JwtRequest jwtRequest) {
	    String username = jwtRequest.getUsername();
        authenticate(username, jwtRequest.getPassword());

	    UserDetails userDetails = this.loadUserByUsername(username);
	    String token = jwtTokenManager.generateToken(userDetails);

	    return JwtResponse.builder()
	            .jwtToken(token)
	            .build();
	}

	private Authentication authenticate(String username, String password) {
	    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
		
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
