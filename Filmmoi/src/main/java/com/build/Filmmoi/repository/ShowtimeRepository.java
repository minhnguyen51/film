package com.build.Filmmoi.repository;



import com.build.Filmmoi.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Showtimes.
 */
@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
}