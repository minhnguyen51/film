package com.booking.movie.Repository;

import com.booking.movie.Repository.Entity.CinemaEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEnity, Integer> {
    
    // Tìm rạp theo tên (tìm kiếm mờ)
    List<CinemaEnity> findByNameContainingIgnoreCase(String name);
} 