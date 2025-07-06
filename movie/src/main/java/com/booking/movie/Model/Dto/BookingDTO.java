package com.booking.movie.Model.Dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BookingDTO {
    private int id;
    private String seat_number;
    private String rating;
    private LocalDateTime booking_time;
}
