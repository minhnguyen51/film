package com.build.Filmmoi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entity class representing the Movies table.
 */
@Entity
@Table(name = "movies")
@Data
public class  Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private Date releaseDate;

    private String posterUrl;
}