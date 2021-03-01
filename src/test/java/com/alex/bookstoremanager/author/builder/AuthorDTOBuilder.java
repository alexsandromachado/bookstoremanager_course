package com.alex.bookstoremanager.author.builder;

import com.alex.bookstoremanager.author.dto.AuthorDTO;

import lombok.Builder;

@Builder
public class AuthorDTOBuilder {
	
    @Builder.Default
    private final Long id = 1L;
    @Builder.Default
    private final String name = "Alexsandro Machado";
    @Builder.Default
    private final int age = 32;
    
    public AuthorDTO buildAuthorDTO() {
    	return new AuthorDTO(id, name, age);
    }

}
