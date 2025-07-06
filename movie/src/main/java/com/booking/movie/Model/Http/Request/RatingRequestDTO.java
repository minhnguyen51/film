package com.booking.movie.Model.Http.Request;

import lombok.Data;

@Data
public class RatingRequestDTO {
    private int movieId;
    private int rating;
}