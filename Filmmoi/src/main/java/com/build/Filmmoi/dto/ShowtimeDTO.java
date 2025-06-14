package com.build.Filmmoi.dto;

import lombok.Data;

import java.time.LocalDateTime;
/**
 * Data Transfer Object for Showtime.
 */
@Data
public class ShowtimeDTO {
    private Integer showtimeId;
    private Integer movieId;
    private String movieTitle;
    private LocalDateTime showtime;
    private Double price;
}
