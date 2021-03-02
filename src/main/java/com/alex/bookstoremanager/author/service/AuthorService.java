package com.alex.bookstoremanager.author.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.bookstoremanager.author.dto.AuthorDTO;
import com.alex.bookstoremanager.author.entity.Author;
import com.alex.bookstoremanager.author.exception.AuthorAlreadyExistsException;
import com.alex.bookstoremanager.author.exception.AuthorNotFoundException;
import com.alex.bookstoremanager.author.mapper.AuthorMapper;
import com.alex.bookstoremanager.author.repository.AuthorRepository;

@Service
public class AuthorService {

    private final static AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    public AuthorDTO create(AuthorDTO authorDTO) {
    	verifyIfExists(authorDTO.getName());
    	Author authorToCreate = authorMapper.toModel(authorDTO);
    	Author createdAuthor = authorRepository.save(authorToCreate);
    	return authorMapper.toDTO(createdAuthor);
    	
    }

    public AuthorDTO findById(Long id) {
    	Author foundAuthor = verifyAndGetAuthor(id);
    	return authorMapper.toDTO(foundAuthor);
    	
    }
    
	private void verifyIfExists(String authorName) {
		authorRepository.findByName(authorName)
    	.ifPresent(author -> {throw new AuthorAlreadyExistsException(authorName); });
	}
	
	public List<AuthorDTO> findAll(){
		return authorRepository.findAll()
				.stream()
				.map(authorMapper :: toDTO)
				.collect(Collectors.toList());
		
	}
	
	public void delete(Long id) {
		verifyAndGetAuthor(id);
		authorRepository.deleteById(id);
	}

	private Author verifyAndGetAuthor(Long id) {
		Author foundAuthor = authorRepository.findById(id)
		    	.orElseThrow(()  -> new AuthorNotFoundException(id));
		return foundAuthor;
	}
	
	
}
