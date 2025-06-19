package com.booking.movie.Controller;

import com.booking.movie.Model.Dto.BookingDTO;
import com.booking.movie.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Map;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    // Đặt vé (check ghế, random user, lưu DB)
    @PostMapping("/book")
    public ResponseEntity<BookingDTO> bookTicket(@RequestBody BookingDTO request) {
        BookingDTO result = bookingService.bookTicket(request);
        return ResponseEntity.ok(result);
    }

    // Thanh toán (giả lập)
    @PostMapping("/payment")
    public ResponseEntity<Map<String, Object>> processPayment(@RequestParam int bookingId, @RequestParam boolean isSuccess) {
        Map<String, Object> result = bookingService.processPayment(bookingId, isSuccess);
        return ResponseEntity.ok(result);
    }
}

