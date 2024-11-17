package com.aitbekov.library.controllers;

import com.aitbekov.library.models.Book;
import com.aitbekov.library.models.Genre;
import com.aitbekov.library.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");
        book.setGenre(Genre.FICTION);

        when(bookService.getAllBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Sample Book"))
                .andExpect(jsonPath("$[0].genre").value("FICTION"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");

        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Book"));
    }

    @Test
    void testGetBookById_NotFound() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book();
        book.setTitle("New Book");
        book.setGenre(Genre.FICTION);

        when(bookService.createBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"New Book\", \"genre\": \"FICTION\", \"price\": 20.99, \"quantity\": 10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Book"));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Updated Book");

        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Book\", \"genre\": \"FICTION\", \"price\": 25.99, \"quantity\": 5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"));
    }

    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testSearchBooksByTitle() throws Exception {
        Book book = new Book();
        book.setTitle("Sample Book");

        when(bookService.searchBooksByTitle("Sample")).thenReturn(List.of(book));

        mockMvc.perform(get("/api/books/search/title?keyword=Sample"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Sample Book"));
    }

    @Test
    void testGetBooksByGenre() throws Exception {
        Book book = new Book();
        book.setTitle("Genre Book");
        book.setGenre(Genre.FICTION);

        when(bookService.getBooksByGenre(Genre.FICTION)).thenReturn(List.of(book));

        mockMvc.perform(get("/api/books/search/genre?genre=FICTION"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Genre Book"))
                .andExpect(jsonPath("$[0].genre").value("FICTION"));
    }
}
