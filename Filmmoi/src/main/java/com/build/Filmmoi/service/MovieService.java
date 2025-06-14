package com.build.Filmmoi.service;



import com.build.Filmmoi.dto.MovieDTO;
import com.build.Filmmoi.entity.Movie;
import com.build.Filmmoi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing Movies.
 */
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Retrieve all movies
    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Save or update a movie
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Convert Entity to DTO
    private MovieDTO convertToDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setMovieId(movie.getMovieId());
        dto.setTitle(movie.getTitle());
        dto.setDurationMinutes(movie.getDurationMinutes());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setPosterUrl(movie.getPosterUrl());
        return dto;
    }

}