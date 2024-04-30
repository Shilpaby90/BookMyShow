package com.example.sapient.service;

import com.example.sapient.model.Movie;
import com.example.sapient.model.Shows;
import com.example.sapient.model.Theatre;
import com.example.sapient.model.TheatreSeat;
import com.example.sapient.model.reponse.ShowsResponse;
import com.example.sapient.model.reponse.TheatreResponse;
import com.example.sapient.model.reponse.TheatreSeatResponse;
import com.example.sapient.model.request.ShowRequest;
import com.example.sapient.repository.MovieRepository;
import com.example.sapient.repository.ShowsRepository;
import com.example.sapient.repository.TheatreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShowsService {
    @Autowired
    private ShowsRepository showsRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<ShowsResponse> getAllShowsByMovieAndDateAndCity(String movieName, LocalDate date, String city) {
        List<ShowsResponse> showsResponses = new ArrayList<>();
       List<Shows> shows =  showsRepository.findByMovieAndDateAndCity(movieName,date,city);
        shows.forEach(shows1 -> {
            List<TheatreSeatResponse> theatreSeatResponseList = new ArrayList<>();
            shows1.getTheatre().getTheatreSeats().forEach(theatreSeat -> {
                        theatreSeatResponseList.add(TheatreSeatResponse.builder()
                                .type(theatreSeat.getType())
                                .capacity(theatreSeat.getCapacity())
                                .price(theatreSeat.getPrice())
                                .build());
                    }
            );
            showsResponses.add(ShowsResponse.builder()
                    .id(shows1.getId())
                    .date(shows1.getDate())
                    .time(shows1.getTime())
                    .theatreResponse(buildTheatreResponse(shows1.getTheatre()))
                    .build());
        });
        return showsResponses;
    }

    private List<TheatreSeatResponse> buildTheatreSeatResponse(List<TheatreSeat> theatreSeats){
        List<TheatreSeatResponse> theatreSeatResponseList = new ArrayList<>();
        theatreSeats.forEach(theatreSeat -> {
                    theatreSeatResponseList.add(TheatreSeatResponse.builder()
                            .type(theatreSeat.getType())
                            .capacity(theatreSeat.getCapacity())
                            .price(theatreSeat.getPrice())
                            .build());
                }
        );
        return theatreSeatResponseList;
    }
    private TheatreResponse buildTheatreResponse(Theatre theatre){
       return TheatreResponse.builder()
                .id(theatre.getId())
                .name(theatre.getName())
                .city(theatre.getCity())
                .quality(theatre.getQuality())
                .address(theatre.getAddress())
                .theatreSeatResponseList(buildTheatreSeatResponse(theatre.getTheatreSeats())).build();
    }
    @Transactional
    public ShowsResponse addShow(ShowRequest showRequest) {
        Shows show = buildShow(showRequest);
        return buildShowResponse(showsRepository.save(show));
    }

    private ShowsResponse buildShowResponse(Shows show) {
        return ShowsResponse.builder()
                .id(show.getId())
                .date(show.getDate())
                .time(show.getTime())
                .theatreResponse(buildTheatreResponse(show.getTheatre()))
                .build();
    }

    private Shows buildShow(ShowRequest showRequest) {
        Theatre theatre = theatreRepository.findById(showRequest.getTheatreId()).orElseThrow(()->
                 new EntityNotFoundException("Theatre Id not found"));
        Movie movie = movieRepository.findById(showRequest.getMovieId()).orElseThrow(()->
                new EntityNotFoundException("Movie Id not found"));
        return Shows.builder()
                .date(showRequest.getDate())
                .time(LocalTime.parse(showRequest.getTime()))
                .theatre(theatre)
                .movie(movie)
                .build();
    }

    @Transactional
    public void deleteShow(UUID showId) {
        showsRepository.deleteById(showId);
    }
}
