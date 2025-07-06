package com.booking.movie.Repository.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int movieId;
    private int roomId;
    private int cinemaId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
} 