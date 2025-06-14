package com.build.Filmmoi.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketResponse {
    private Integer bookingId;
    private String movieTitle;
    private LocalDateTime showtime;
    private List<String> seatNumbers;
    private Double totalPrice;
    private LocalDateTime bookingTime;
} 