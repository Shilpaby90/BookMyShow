package com.example.sapient.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowsResponse {
    private UUID id;

    private LocalDate date;

    private LocalTime time;

    private TheatreResponse theatreResponse;

}
