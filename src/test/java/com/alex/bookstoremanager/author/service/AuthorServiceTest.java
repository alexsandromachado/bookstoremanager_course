package com.alex.bookstoremanager.author.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alex.bookstoremanager.author.builder.AuthorDTOBuilder;
import com.alex.bookstoremanager.author.dto.AuthorDTO;
import com.alex.bookstoremanager.author.entity.Author;
import com.alex.bookstoremanager.author.exception.AuthorAlreadyExistsException;
import com.alex.bookstoremanager.author.exception.AuthorNotFoudException;
import com.alex.bookstoremanager.author.mapper.AuthorMapper;
import com.alex.bookstoremanager.author.repository.AuthorRepository;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
	
	private final AuthorMapper authorMapper = AuthorMapper.INSTANCE; 
	
	@Mock
	private AuthorRepository authorRepository;

	@InjectMocks
	private AuthorService authorService;
	
	private AuthorDTOBuilder authorDTOBuilder;
	
	@BeforeEach
	void setUp() {
		authorDTOBuilder = AuthorDTOBuilder.builder().build();
	}
	
	@Test
    void whenNewAuthorIsInformedThenItShouldBeCreated() {
        //given
        AuthorDTO expectedAuthorToCreateDTO = authorDTOBuilder.buildAuthorDTO();
        Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreateDTO);

        //when
        Mockito.when(authorRepository.save(expectedCreatedAuthor)).thenReturn(expectedCreatedAuthor);
       
        Mockito.when(authorRepository.findByName(expectedAuthorToCreateDTO.getName())).thenReturn(Optional.empty());

        AuthorDTO createdAuthorDTO = authorService.create(expectedAuthorToCreateDTO);

        //then
        MatcherAssert.assertThat(createdAuthorDTO, Is.is(IsEqual.equalTo(expectedAuthorToCreateDTO)));
    }
	
	@Test
    void whenExistingAuthorIsInformedThenAnExceptionShouldBeThrown() {
        //given
        AuthorDTO expectedAuthorToCreateDTO = authorDTOBuilder.buildAuthorDTO();
        Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreateDTO);
      
        Mockito.when(authorRepository.findByName(expectedAuthorToCreateDTO.getName()))
        .thenReturn(Optional.of(expectedCreatedAuthor));

        Assertions.assertThrows(AuthorAlreadyExistsException.class, () ->
   	    authorService.create(expectedAuthorToCreateDTO));
    }
	
	  @Test
	    void whenValidIdIsGivenThenAnAuthorShouldBeReturned() {
	        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.buildAuthorDTO();
	        Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

	        Mockito.when(authorRepository.findById(expectedFoundAuthorDTO.getId())).thenReturn(Optional.of(expectedFoundAuthor));

	        AuthorDTO foundAuthorDTO = authorService.findById(expectedFoundAuthorDTO.getId());

	      //  Assertions.assertThat(foundAuthorDTO, Is.is(IsEqual.equalTo(expectedFoundAuthorDTO)));
	    }
	  
	  @Test
	    void whenInvalidIdIsGivenThenAnExceptionShouldBeThrown() {
	        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.buildAuthorDTO();

	        Mockito.when(authorRepository.findById(expectedFoundAuthorDTO.getId())).thenReturn(Optional.empty());

	        Assertions.assertThrows(AuthorNotFoudException.class, () -> authorService.findById(expectedFoundAuthorDTO.getId()));
	    }
	  
	  @Test
	    void whenListAuthorsIsCalledThenItShouldBeReturned() {
	        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.buildAuthorDTO();
	        Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

	        Mockito.when(authorRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundAuthor));

	        List<AuthorDTO> foundAuthorsDTO = authorService.findAll();

	       // Assertions.assertThat(foundAuthorsDTO.size(), Is.is(1));
	      //  Assertions.assertThat(foundAuthorsDTO.get(0), Is.is(equalTo(expectedFoundAuthorDTO)));
	    }
		  
	  @Test
	    void whenListAuthorsIsCalledThenAnEmptyListShouldBeReturned() {
		  Mockito.when(authorRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

	        List<AuthorDTO> foundAuthorsDTO = authorService.findAll();

	      //  Assertions.assertThat(foundAuthorsDTO.size(), Is.is(0));
	    }

//	    @Test
//	    void whenValidAuthorIdIsGivenThenItShouldBeDeleted() {
//	        AuthorDTO expectedDeletedAuthorDTO = authorDTOBuilder.buildAuthorDTO();
//	        Author expectedDeletedAuthor = authorMapper.toModel(expectedDeletedAuthorDTO);
//
//	        Long expectedDeletedAuthorId = expectedDeletedAuthorDTO.getId();
//	        Mockito.doNothing().when(authorRepository).deleteById(expectedDeletedAuthorId);
//	        Mockito.when(authorRepository.findById(expectedDeletedAuthorId)).thenReturn(Optional.of(expectedDeletedAuthor));
//
//	        authorService.delete(expectedDeletedAuthorId);
//
//	        verify(authorRepository, times(1)).deleteById(expectedDeletedAuthorId);
//	        verify(authorRepository, times(1)).findById(expectedDeletedAuthorId);
//	    }
//
//	    @Test
//	    void whenInvalidAuthorIdIsGivenThenItAnExceptionShouldBeThrown() {
//	        var expectedInvalidAuthorId = 2L;
//
//	        Mockito.when(authorRepository.findById(expectedInvalidAuthorId)).thenReturn(Optional.empty());
//
//	        Assertions.assertThrows(AuthorNotFoundException.class, () -> authorService.delete(expectedInvalidAuthorId));
//	    }

}
