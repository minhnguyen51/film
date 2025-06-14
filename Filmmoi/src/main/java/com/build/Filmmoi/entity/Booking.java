package com.build.Filmmoi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entity class representing the Bookings table.
 */
@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(name = "booking_time", nullable = false, updatable = false)
    private LocalDateTime bookingTime;

    @PrePersist
    protected void onCreate() {
        this.bookingTime = LocalDateTime.now();
    }
}