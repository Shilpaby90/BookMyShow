package com.example.sapient.controller;

import com.example.sapient.model.reponse.ShowsResponse;
import com.example.sapient.model.request.ShowRequest;
import com.example.sapient.service.MovieService;
import com.example.sapient.service.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shows")
public class ShowsController {

    @Autowired
    private ShowsService showsService;
    @Autowired
    private MovieService movieService;

    @GetMapping( "/getAllShowsByMovieAndDateAndCity")
    public ResponseEntity<List<ShowsResponse>> getAllShowsByMovieAndDateAndCity(
            @RequestParam String movieName,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam String city){
        List<ShowsResponse> showsResponses = showsService.getAllShowsByMovieAndDateAndCity(movieName,date,city);
        return new ResponseEntity<>(showsResponses, HttpStatus.OK);
    }
    @PostMapping( "/add")
    public ResponseEntity<ShowsResponse> addShow(@Validated @RequestBody ShowRequest showRequest){
        ShowsResponse showsResponse = showsService.addShow(showRequest);
        return new ResponseEntity<>(showsResponse,HttpStatus.CREATED);
    }

    @DeleteMapping( "/{showId}")
    public ResponseEntity<String> deleteShow(@PathVariable UUID showId){
        showsService.deleteShow(showId);
        return new ResponseEntity<>("Successfully Deleted the show",HttpStatus.OK);
    }
}
