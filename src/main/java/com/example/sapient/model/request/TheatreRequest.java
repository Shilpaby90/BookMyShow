package com.example.sapient.model.request;

import com.example.sapient.model.reponse.TheatreSeatResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatreRequest {

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @NotNull
    private String quality;

    @NotNull
    private List<TheatreSeatRequest> theatreSeatRequests;
}
