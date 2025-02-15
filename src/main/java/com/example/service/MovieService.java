package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Movie;
import com.example.repository.MovieRepository;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Returns a list of movies along with their reviews
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    // Accepts movie details and stores them in the database
    public Movie addMovie(String title, String genre, int year) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be empty");
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setYear(year);

        return movieRepository.save(movie);
    }

}
