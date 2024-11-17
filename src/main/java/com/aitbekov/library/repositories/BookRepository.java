package com.aitbekov.library.repositories;

import com.aitbekov.library.models.Book;
import com.aitbekov.library.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> findByTitleContaining(@Param("keyword") String keyword);


    List<Book> findByAuthorId(Long authorId);

    List<Book> findByGenre(Genre genre);
}
