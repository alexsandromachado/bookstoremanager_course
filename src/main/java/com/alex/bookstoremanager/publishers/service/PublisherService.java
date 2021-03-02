package com.alex.bookstoremanager.publishers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
}
