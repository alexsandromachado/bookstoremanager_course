package com.alex.bookstoremanager.publishers.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {
	
	
	private Long id;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(max = 50)
	private String code;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate foundationDate;

}
