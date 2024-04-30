package com.example.sapient.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String city;

    private String address;

    private String quality;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "theatre", cascade = CascadeType.ALL)
    private List<TheatreSeat> theatreSeats;
}
