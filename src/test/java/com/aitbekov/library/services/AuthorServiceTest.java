package com.aitbekov.library.services;

import com.aitbekov.library.models.Author;
import com.aitbekov.library.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    private final AuthorRepository authorRepository = mock(AuthorRepository.class);
    private final AuthorService authorService = new AuthorService(authorRepository);

    @Test
    void testGetAuthorById() {
        Author author = new Author();
        author.setId(1L);
        author.setName("John Doe");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.getAuthorById(1L);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    void testCreateAuthor() {
        Author author = new Author();
        author.setName("Jane Doe");

        when(authorRepository.save(author)).thenReturn(author);

        Author savedAuthor = authorService.createAuthor(author);
        assertEquals("Jane Doe", savedAuthor.getName());
    }

    @Test
    void testUpdateAuthor() {
        Author existingAuthor = new Author();
        existingAuthor.setId(1L);
        existingAuthor.setName("Old Name");

        Author updatedDetails = new Author();
        updatedDetails.setName("New Name");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(existingAuthor)).thenReturn(existingAuthor);

        Author updatedAuthor = authorService.updateAuthor(1L, updatedDetails);
        assertEquals("New Name", updatedAuthor.getName());
    }

    @Test
    void testDeleteAuthor() {
        doNothing().when(authorRepository).deleteById(1L);

        assertDoesNotThrow(() -> authorService.deleteAuthor(1L));
        verify(authorRepository, times(1)).deleteById(1L);
    }
}
