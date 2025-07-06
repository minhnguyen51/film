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

    // Đánh giá phim
    public Map<String, Object>  rateMovie(int movieId, int rating) {
        // Kiểm tra rating hợp lệ (1-5)
        if (rating < 1 || rating > 5) {
            return Map.of(
                "success", false,
                "message", "Rating phải từ 1 đến 5 sao"
            );
        }
        // Lưu rating vào database
        BookingEnity ratingEntity = new BookingEnity();
        ratingEntity.setSeatNumber("RATING_" + movieId);
        ratingEntity.setRating(String.valueOf(rating));
        ratingEntity.setBookingTime(LocalDateTime.now());
        bookingRepository.save(ratingEntity);

        return Map.of(
            "success", true,
            "message", "Đánh giá thành công: " + rating + " sao",
            "movieId", movieId,
            "rating", rating
        );
    }

    // Lấy danh sách phim được đánh giá cao nhất
    public List<Map<String, Object>> getTopRatedMovies() {
        // Lấy tất cả rating từ database
        List<BookingEnity> allRatings = bookingRepository.findAll();
        
        // Map để lưu tổng rating và số lượng đánh giá cho mỗi phim
        Map<String, Double> movieTotalRatings = new HashMap<>();
        Map<String, Integer> movieRatingCount = new HashMap<>();
        
        for (BookingEnity booking : allRatings) {
            // Chỉ xử lý các booking có seat_number bắt đầu bằng "RATING_"
            if (booking.getSeatNumber() != null && booking.getSeatNumber().startsWith("RATING_")) {
                String movieId = booking.getSeatNumber().substring(7); // Bỏ "RATING_" prefix
                String ratingStr = booking.getRating();
                
                try {
                    double rating = Double.parseDouble(ratingStr);
                    
                    // Cộng dồn rating
                    movieTotalRatings.put(movieId, 
                        movieTotalRatings.getOrDefault(movieId, 0.0) + rating);
                    movieRatingCount.put(movieId, 
                        movieRatingCount.getOrDefault(movieId, 0) + 1);
                } catch (NumberFormatException e) {
                    // Bỏ qua nếu rating không phải số
                }
            }
        }
        
        // Tính trung bình rating và tạo danh sách kết quả
        List<Map<String, Object>> topRatedMovies = new ArrayList<>();
        for (String movieId : movieTotalRatings.keySet()) {
            double totalRating = movieTotalRatings.get(movieId);
            int count = movieRatingCount.get(movieId);
            double averageRating = totalRating / count;
            
            Map<String, Object> movieRating = new HashMap<>();
            movieRating.put("movieId", movieId);
            movieRating.put("averageRating", Math.round(averageRating * 10.0) / 10.0);
            movieRating.put("totalRatings", count);
            topRatedMovies.add(movieRating);
        }
        
        // Sắp xếp theo rating từ cao đến thấp
        topRatedMovies.sort((a, b) -> {
            double ratingA = (Double) a.get("averageRating");
            double ratingB = (Double) b.get("averageRating");
            return Double.compare(ratingB, ratingA); // Sắp xếp giảm dần
        });
        
        return topRatedMovies;
    }
}
