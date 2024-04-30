package com.example.sapient.controller;

import com.example.sapient.model.reponse.TheatreResponse;
import com.example.sapient.model.request.TheatreRequest;
import com.example.sapient.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping( "/add")
    public ResponseEntity<TheatreResponse> addTheatre(@Validated @RequestBody TheatreRequest theatreRequest){
     TheatreResponse theatreResponse = theatreService.addTheatre(theatreRequest);
     return new ResponseEntity<>(theatreResponse,HttpStatus.CREATED);
    }
}
