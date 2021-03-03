package com.alex.bookstoremanager.publishers.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.bookstoremanager.author.dto.AuthorDTO;
import com.alex.bookstoremanager.author.entity.Author;
import com.alex.bookstoremanager.author.exception.AuthorAlreadyExistsException;
import com.alex.bookstoremanager.publishers.dto.PublisherDTO;
import com.alex.bookstoremanager.publishers.entity.Publisher;
import com.alex.bookstoremanager.publishers.exception.PublisherAlreadyExistsException;
import com.alex.bookstoremanager.publishers.exception.PublisherNotFoundException;
import com.alex.bookstoremanager.publishers.mapper.PublisherMapper;
import com.alex.bookstoremanager.publishers.repository.PublisherRepository;

@Service
public class PublisherService {
	
	public static final PublisherMapper publishermapper = PublisherMapper.INSTANCE;
	
	private PublisherRepository publisherRepository;

	@Autowired
	public PublisherService(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}
	
    public PublisherDTO create(PublisherDTO publisherDTO) {
    	verifyIfExists(publisherDTO.getName(), publisherDTO.getCode());
    	Publisher publisherToCreate = publishermapper.toModel(publisherDTO);
    	Publisher createdPublisher = publisherRepository.save(publisherToCreate);
    	return publishermapper.toDTO(createdPublisher);
    	
    }
    
    public PublisherDTO findById(Long id) {
    	return publisherRepository.findById(id)
    			.map(publishermapper::toDTO)
    			.orElseThrow(() -> new PublisherNotFoundException(id));  
    }
	
	public List<PublisherDTO> findAll(){
		return publisherRepository.findAll()
		.stream()
		.map(publishermapper::toDTO)
		.collect(Collectors.toList());		
	}
	
	public void delete(Long id) {
		verifyIfExists(id);
		publisherRepository.deleteById(id); 
		
	}
		
	private void verifyIfExists(String name, String code) {
		Optional<Publisher> duplicatedPublisher = publisherRepository.findByNameOrCode(name, code);
    	if (duplicatedPublisher.isPresent()) {
    		throw new PublisherAlreadyExistsException(name, code);
    	}
	}
	
	private void verifyIfExists(Long id) {
		publisherRepository.findById(id)
		.orElseThrow(() -> new PublisherNotFoundException(id));
	}
}
