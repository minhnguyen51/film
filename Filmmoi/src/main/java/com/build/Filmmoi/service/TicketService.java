package com.build.Filmmoi.service;

import com.build.Filmmoi.dto.TicketRequest;
import com.build.Filmmoi.dto.TicketResponse;

public interface TicketService {
    TicketResponse createTicket(TicketRequest request);
    TicketResponse getTicket(Integer bookingId);
} 