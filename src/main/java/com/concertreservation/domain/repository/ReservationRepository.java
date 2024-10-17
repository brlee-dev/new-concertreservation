package com.concertreservation.domain.repository;

import com.concertreservation.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByDateAndSeatNumber(LocalDate date, int seatNumber);
}
