package com.example.sapient.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatreResponse {

    private UUID id;

    private String name;

    private String city;

    private String address;

    private String quality;

    private List<TheatreSeatResponse> theatreSeatResponseList = new ArrayList<>();
}
