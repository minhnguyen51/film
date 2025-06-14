package com.build.Filmmoi.service.impl;

import com.build.Filmmoi.dto.TicketRequest;
import com.build.Filmmoi.dto.TicketResponse;
import com.build.Filmmoi.entity.Booking;
import com.build.Filmmoi.entity.Seat;
import com.build.Filmmoi.entity.Showtime;
import com.build.Filmmoi.entity.User;
import com.build.Filmmoi.repository.BookingRepository;
import com.build.Filmmoi.repository.SeatRepository;
import com.build.Filmmoi.repository.ShowtimeRepository;
import com.build.Filmmoi.repository.UserRepository;
import com.build.Filmmoi.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TicketResponse createTicket(TicketRequest request) {
        // Validate and get showtime
        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new EntityNotFoundException("Showtime not found"));

        // Validate and get user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowtime(showtime);
        booking.setTotalPrice(calculateTotalPrice(showtime, request.getSeatNumbers().size()));
        booking = bookingRepository.save(booking);

        // Create seats
        List<Seat> seats = new ArrayList<>();
        for (String seatNumber : request.getSeatNumbers()) {
            Seat seat = new Seat();
            seat.setBooking(booking);
            seat.setSeatNumber(seatNumber);
            seats.add(seat);
        }
        seatRepository.saveAll(seats);

        return mapToTicketResponse(booking, seats);
    }

    @Override
    public TicketResponse getTicket(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        
        List<Seat> seats = seatRepository.findByBooking(booking);
        return mapToTicketResponse(booking, seats);
    }

    private Double calculateTotalPrice(Showtime showtime, int numberOfSeats) {
        // You can implement your own pricing logic here
        return showtime.getPrice() * numberOfSeats;
    }

    private TicketResponse mapToTicketResponse(Booking booking, List<Seat> seats) {
        TicketResponse response = new TicketResponse();
        response.setBookingId(booking.getBookingId());
        response.setMovieTitle(booking.getShowtime().getMovie().getTitle());
        response.setShowtime(booking.getShowtime().getShowtime());
        response.setSeatNumbers(seats.stream().map(Seat::getSeatNumber).toList());
        response.setTotalPrice(booking.getTotalPrice());
        response.setBookingTime(booking.getBookingTime());
        return response;
    }
} 