package com.booking.movie.Repository;

import com.booking.movie.Repository.Entity.BookingEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface BookingRepository extends JpaRepository<BookingEnity, Integer> {
    Optional<BookingEnity> findBySeatNumberAndBookingTime(String seatNumber, LocalDateTime bookingTime);
}