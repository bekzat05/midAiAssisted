package com.aitbekov.library.services;

import com.aitbekov.library.models.Genre;
import com.aitbekov.library.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre[] getAllGenres() {
        return genreRepository.findAllGenres();
    }
}
