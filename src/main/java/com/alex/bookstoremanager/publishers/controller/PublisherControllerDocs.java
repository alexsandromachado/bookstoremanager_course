package com.alex.bookstoremanager.publishers.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.alex.bookstoremanager.publishers.dto.PublisherDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Publishers management")
public interface PublisherControllerDocs {
	
	@ApiOperation(value = "Publisher cration operation")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success Publisher creation"),
			@ApiResponse(code = 400, message = "Missing required fields, wrong field range value or publisher already registred on system")
	})
	PublisherDTO create(PublisherDTO publisherDTO);
	
	@ApiOperation(value = "Find publisher by id operation")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success Publisher found"),
			@ApiResponse(code = 400, message = "Publisher not found error	")
	})
	PublisherDTO findById(Long id);
	
	@ApiOperation(value = "List all registred publishers")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "return all registred Publishers"),
	})
	List<PublisherDTO> findAll();

}
