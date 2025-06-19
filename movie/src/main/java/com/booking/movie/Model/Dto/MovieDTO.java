package com.booking.movie.Model.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDTO {
    private int id;
    private String title;
    private String description;
    private String poster_url;
    private LocalDate release_date;
    private String genre;
    private String director;
    private String cast;

}
