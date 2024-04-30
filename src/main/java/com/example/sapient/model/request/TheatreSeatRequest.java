package com.example.sapient.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatreSeatRequest {

    @NotNull
    private String type;

    @NotNull
    private Integer capacity;

    @NotNull
    private double price;
}
