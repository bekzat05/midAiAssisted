package com.aitbekov.library.services;

import com.aitbekov.library.models.Book;
import com.aitbekov.library.models.Genre;
import com.aitbekov.library.repositories.BookRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private final BookRepository bookRepository = mock(BookRepository.class);
    private final BookService bookService = new BookService(bookRepository);

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(1L);
        assertTrue(result.isPresent());
        assertEquals("Sample Book", result.get().getTitle());
    }

    @Test
    void testSearchBooksByTitle() {
        Book book = new Book();
        book.setTitle("Sample Book");

        when(bookRepository.findByTitleContaining("Sample")).thenReturn(List.of(book));

        List<Book> result = bookService.searchBooksByTitle("Sample");
        assertEquals(1, result.size());
        assertEquals("Sample Book", result.get(0).getTitle());
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("New Book");
        book.setGenre(Genre.FICTION);

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.createBook(book);
        assertEquals("New Book", savedBook.getTitle());
        assertEquals(Genre.FICTION, savedBook.getGenre());
    }

    @Test
    void testUpdateBook() {
        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Old Title");

        Book updatedDetails = new Book();
        updatedDetails.setTitle("New Title");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book updatedBook = bookService.updateBook(1L, updatedDetails);
        assertEquals("New Title", updatedBook.getTitle());
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        assertDoesNotThrow(() -> bookService.deleteBook(1L));
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
