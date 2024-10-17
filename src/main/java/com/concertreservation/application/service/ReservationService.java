package com.concertreservation.application.service;

import com.concertreservation.application.dto.ReservationDto;
import com.concertreservation.domain.exception.SeatNotAvailableException;
import com.concertreservation.domain.model.Reservation;
import com.concertreservation.domain.model.User;
import com.concertreservation.domain.repository.ReservationRepository;
import com.concertreservation.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void reserveSeat(String userId, String date, int seatNumber) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + userId));

        LocalDate reservationDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        if (reservationRepository.existsByDateAndSeatNumber(reservationDate, seatNumber)) {
            throw new SeatNotAvailableException("Seat " + seatNumber + " is already reserved for the date " + date);
        }

        Reservation reservation = new Reservation(user, reservationDate, seatNumber, false);
        reservationRepository.save(reservation);
    }
}
