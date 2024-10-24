package com.concertreservation.interfaces.controller;

import com.concertreservation.application.service.QueueTokenService;
import com.concertreservation.application.service.ReservationService;
import com.concertreservation.domain.model.ConcertDate;
import com.concertreservation.domain.model.QueueToken;
import com.concertreservation.domain.model.Seat;
import com.concertreservation.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 가능한 날짜 목록 조회
    @GetMapping("/dates")
    public List<ConcertDate> getAvailableDates() {
        return reservationService.getAvailableDates();
    }

    // 특정 날짜의 예약 가능한 좌석 목록 조회
    @GetMapping("/seats")
    public List<Seat> getAvailableSeats(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reservationService.getAvailableSeats(date);
    }

    // 좌석 예약 요청
    @PostMapping("/reserve")
    public String reserveSeat(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                              @RequestParam int seatNumber, HttpServletRequest request) {

        QueueToken token = (QueueToken) request.getAttribute("token");
        User user = token.getUser();

        reservationService.reserveSeat(date, seatNumber, user);

        return "좌석이 예약되었습니다.";
    }
}




