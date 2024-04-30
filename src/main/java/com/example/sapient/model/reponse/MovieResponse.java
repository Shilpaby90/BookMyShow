package com.example.sapient.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieResponse {

    private UUID id;

    private String name;

    private String type;

    private LocalDate releasedOn;

}
