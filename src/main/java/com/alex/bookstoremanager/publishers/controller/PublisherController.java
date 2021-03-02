package com.alex.bookstoremanager.publishers.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alex.bookstoremanager.publishers.dto.PublisherDTO;
import com.alex.bookstoremanager.publishers.service.PublisherService;

@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController implements PublisherControllerDocs{
	
	PublisherService publisherService;

	@Autowired
	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PublisherDTO create(@RequestBody @Valid PublisherDTO publisherDTO) {
		return publisherService.create(publisherDTO);
	}
	
	

}
