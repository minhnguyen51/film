package com.build.Filmmoi.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing the Seats table.
 */
@Entity
@Table(name = "seats")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private String seatNumber;
}
