package com.build.Filmmoi.dto;

import lombok.Data;

import java.util.Date;

/**
 * Data Transfer Object for Movie.
 */
@Data
public class MovieDTO {
    private Integer movieId;
    private String title;
    private Integer durationMinutes;
    private Date releaseDate;
    private String posterUrl;
}