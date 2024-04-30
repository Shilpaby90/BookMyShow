package com.example.sapient.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieRequest {

    @NotNull
    private String name;

    @NotNull
    private String type;

    @NotNull
    private LocalDate releasedOn;
}
