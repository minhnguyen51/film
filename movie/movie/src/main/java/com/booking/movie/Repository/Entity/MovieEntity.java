package com.booking.movie.Repository.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String poster_url;

    private LocalDate releaseDate;
    private String genre;
    private String director;
    private String cast;
}
