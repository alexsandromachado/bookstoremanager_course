package com.alex.bookstoremanager.publishers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.bookstoremanager.publishers.service.PublisherService;

@RestController
@RequestMapping("api/v1/publishers")
public class PublisherController implements PublisherControllerDocs{
	
	PublisherService publisherService;

	@Autowired
	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}
	
	

}
