package com.example.sapient.controller;


import com.example.sapient.model.reponse.MovieResponse;
import com.example.sapient.model.request.MovieRequest;
import com.example.sapient.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping( "/getAll")
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        List<MovieResponse> movieResponses = movieService.getAllMovies();
        return new ResponseEntity<>(movieResponses, HttpStatus.OK);
    }

    @PostMapping( "/add")
    public ResponseEntity<MovieResponse> addMovie(@Validated @RequestBody MovieRequest movieRequest){
        MovieResponse movieResponse = movieService.addMovie(movieRequest);
        return new ResponseEntity<>(movieResponse,HttpStatus.CREATED);
    }

    @DeleteMapping( "/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable UUID movieId){
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Successfully Deleted the movie",HttpStatus.OK);
    }
}
