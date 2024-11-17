package com.aitbekov.library.repositories;

import com.aitbekov.library.models.Genre;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepository {
    public Genre[] findAllGenres() {
        return Genre.values();
    }
}
