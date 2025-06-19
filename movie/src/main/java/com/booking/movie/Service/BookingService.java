package com.booking.movie.Service;

import com.booking.movie.Model.Dto.BookingDTO;
import com.booking.movie.Repository.Entity.BookingEnity;
import com.booking.movie.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public BookingDTO bookTicket(BookingDTO req) {
        // 1. Kiểm tra ghế đã được đặt chưa (theo seat_number và booking_time)
        Optional<BookingEnity> existingBooking = bookingRepository.findBySeatNumberAndBookingTime(
                req.getSeat_number(), req.getBooking_time()
        );


        BookingDTO response = new BookingDTO();
        response.setSeat_number(req.getSeat_number());

        if (existingBooking.isPresent()) {
            // Ghế đã được đặt -> trả response thông báo ghế đã bị block
            response.setRating("BLOCKED");
            return response;
        }

        // 2. Random user nếu chưa có rating

        if (req.getRating() == null || req.getRating().isEmpty()) {
            req.setRating(randomUserInfo());
        }

        // 3. Gán thời gian đặt vé nếu chưa có
        if (req.getBooking_time() == null) {
            req.setBooking_time(LocalDateTime.now());
        }

        // 4. Lưu booking
        BookingEnity entity = new BookingEnity();
        entity.setSeatNumber(req.getSeat_number());
        entity.setRating(req.getRating());
        entity.setBookingTime(req.getBooking_time());
        bookingRepository.save(entity);

        // 5. Trả thông tin thành công kèm QR giả lập
        response.setRating("QR: https://fake-qr.com/booking/" + entity.getId());
        response.setBooking_time(req.getBooking_time());
        return response;
    }
    private String randomUserInfo() {
        String[] names = {"Nguyen Van A", "Tran Thi B", "Le Van C", "Pham Thi D"};
        return names[new java.util.Random().nextInt(names.length)];
    }
    public Map<String, Object> processPayment(int bookingId, boolean isSuccess) {
        return Map.of();
    }
}
