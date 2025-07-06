package com.booking.movie.Controller;

import com.booking.movie.Model.Dto.BookingDTO;
import com.booking.movie.Model.Http.Request.RatingRequestDTO;
import com.booking.movie.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
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

   /* // Đánh giá phim
    @PostMapping("/rate")
    public ResponseEntity<Map<String, Object>> rateMovie(@RequestParam int movieId, @RequestParam int rating) {
        Map<String, Object> result = bookingService.rateMovie(movieId, rating);
        return ResponseEntity.ok(result);
    }*/
    @PostMapping("/rate")
    public ResponseEntity<Map<String, Object>> rateMovie(@RequestBody RatingRequestDTO request) {
        Map<String, Object> result = bookingService.rateMovie(request.getMovieId(), request.getRating());
        return ResponseEntity.ok(result);
    }
    // Lấy danh sách phim được đánh giá cao nhất
    @GetMapping("/top-rated")
    public ResponseEntity<List<Map<String, Object>>> getTopRatedMovies() {
        List<Map<String, Object>> result = bookingService.getTopRatedMovies();
        return ResponseEntity.ok(result);
    }
}

