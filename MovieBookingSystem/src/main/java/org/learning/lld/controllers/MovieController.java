package org.learning.lld.controllers;

import lombok.NonNull;
import org.learning.lld.services.MovieService;

public class MovieController {
    private final MovieService movieService;

    public MovieController(@NonNull final MovieService movieService) {
        this.movieService = movieService;
    }

    public String createMovie(@NonNull final String movieName) {
        return this.movieService.createMovie(movieName).getId();
    }
}
