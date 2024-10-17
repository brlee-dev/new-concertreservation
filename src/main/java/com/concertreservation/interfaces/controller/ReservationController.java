package com.concertreservation.interfaces.controller;

import com.concertreservation.application.service.ReservationService;
import com.concertreservation.domain.exception.SeatNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveSeat(@RequestParam long userId,
                                              @RequestParam String date,
                                              @RequestParam int seatNumber) {
        reservationService.reserveSeat(userId, date, seatNumber);
        return ResponseEntity.ok("Seat reserved successfully");
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<String> handleSeatNotAvailableException(SeatNotAvailableException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
