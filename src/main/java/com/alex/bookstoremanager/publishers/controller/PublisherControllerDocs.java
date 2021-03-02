package com.alex.bookstoremanager.publishers.controller;

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

}
