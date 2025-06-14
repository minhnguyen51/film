package com.build.Filmmoi.controler;

import com.build.Filmmoi.dto.TicketRequest;
import com.build.Filmmoi.dto.TicketResponse;
import com.build.Filmmoi.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.createTicket(request));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(ticketService.getTicket(bookingId));
    }
} 