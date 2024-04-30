package com.example.sapient.service;

import com.example.sapient.model.Movie;
import com.example.sapient.model.reponse.MovieResponse;
import com.example.sapient.model.request.MovieRequest;
import com.example.sapient.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieResponse> getAllMovies() {
        List<MovieResponse> movieResponses = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> {
            movieResponses.add(MovieResponse.builder()
                    .id(movie.getId())
                            .name(movie.getName())
                            .type(movie.getType())
                            .releasedOn(movie.getReleasedOn())
                    .build());
        });
        return movieResponses;
    }

    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = buildMovie(movieRequest);
        return buildMovieResponse(movieRepository.save(movie));
    }

    private MovieResponse buildMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .name(movie.getName())
                .type(movie.getType())
                .releasedOn(movie.getReleasedOn())
                .build();
    }

    private Movie buildMovie(MovieRequest movieRequest) {
        return Movie.builder()
                .name(movieRequest.getName())
                .type(movieRequest.getType())
                .releasedOn(movieRequest.getReleasedOn())
                .build();
    }

    public void deleteMovie(UUID movieId) {
        movieRepository.deleteById(movieId);
    }
}
