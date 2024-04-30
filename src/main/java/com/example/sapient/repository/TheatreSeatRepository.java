package com.example.sapient.repository;

import com.example.sapient.model.TheatreSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TheatreSeatRepository extends JpaRepository<TheatreSeat, UUID> {
}
