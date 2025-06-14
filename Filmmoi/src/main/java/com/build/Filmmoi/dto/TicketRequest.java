package com.build.Filmmoi.dto;

import lombok.Data;
import java.util.List;

@Data
public class TicketRequest {
    private Integer showtimeId;
    private List<String> seatNumbers;
    private Integer userId;
} 