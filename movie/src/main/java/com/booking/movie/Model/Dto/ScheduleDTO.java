package com.booking.movie.Model.Dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScheduleDTO {
    private int id;
    private int movieId;
    private int roomId;
    private int cinemaId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
    private String status;
}