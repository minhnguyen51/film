package com.booking.movie.Repository.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public class BookingEnity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rating;
    @Column(name = "seat_number")
    private String seatNumber;
    @Column(name = "booking_time")
    private LocalDateTime bookingTime;
}
