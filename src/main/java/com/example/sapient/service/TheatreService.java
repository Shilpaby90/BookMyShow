package com.example.sapient.service;

import com.example.sapient.model.Theatre;
import com.example.sapient.model.TheatreSeat;
import com.example.sapient.model.reponse.TheatreResponse;
import com.example.sapient.model.reponse.TheatreSeatResponse;
import com.example.sapient.model.request.TheatreRequest;
import com.example.sapient.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public TheatreResponse addTheatre(TheatreRequest theatreRequest) {
        Theatre theatre = buildTheatre(theatreRequest);
        return buildTheatreResponse(theatreRepository.save(theatre));
    }

    private TheatreResponse buildTheatreResponse(Theatre theatre) {
        List<TheatreSeatResponse> theatreSeatResponseList = new ArrayList<>();
        theatre.getTheatreSeats().forEach(theatreSeat -> {
            theatreSeatResponseList.add(TheatreSeatResponse.builder()
                    .type(theatreSeat.getType())
                            .capacity(theatreSeat.getCapacity())
                            .price(theatreSeat.getPrice())
                            .build());
                }
        );
        return TheatreResponse.builder()
                .id(theatre.getId())
                .name(theatre.getName())
                .city(theatre.getCity())
                .address(theatre.getAddress())
                .quality(theatre.getQuality())
                .theatreSeatResponseList(theatreSeatResponseList)
                .build();
    }

    private Theatre buildTheatre(TheatreRequest theatreRequest) {
        Theatre theatre = Theatre.builder()
                .name(theatreRequest.getName())
                .city(theatreRequest.getCity())
                .address(theatreRequest.getAddress())
                .quality(theatreRequest.getQuality())
                .build();
        List<TheatreSeat> theatreSeatList = new ArrayList<>();
        theatreRequest.getTheatreSeatRequests().forEach(theatreSeatRequest -> {
            theatreSeatList.add(TheatreSeat.builder().type(theatreSeatRequest.getType())
                    .capacity(theatreSeatRequest.getCapacity())
                            .theatre(theatre)
                    .price(theatreSeatRequest.getPrice())
                    .build());
                }
        );
        theatre.setTheatreSeats(theatreSeatList);
        return theatre;
    }
}
