package com.booking.movie.Repository;

import com.booking.movie.Repository.Entity.RoomEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEnity, Integer> {
    // Tìm phòng theo tên (tìm kiếm mờ)
    List<RoomEnity> findByNameContainingIgnoreCase(String name);
} 