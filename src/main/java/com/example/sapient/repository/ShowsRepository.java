package com.example.sapient.repository;

import com.example.sapient.model.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShowsRepository extends JpaRepository<Shows, UUID> {
    @Query(value = "select s from Shows s where s.movie.name=?1 and" +
            " s.date=?2 and s.theatre.city=?3"
    )
    List<Shows> findByMovieAndDateAndCity(String movieName, LocalDate date, String city);
}
