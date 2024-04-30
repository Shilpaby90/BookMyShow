package com.example.sapient.model.request;

import jakarta.validation.constraints.Pattern;
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
public class ShowRequest {
    private LocalDate date;

    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "Invalid time format")
    private String time;

    private UUID theatreId;

    private UUID movieId;
}
