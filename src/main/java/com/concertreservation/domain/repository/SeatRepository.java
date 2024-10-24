package com.concertreservation.domain.repository;

import com.concertreservation.domain.model.Seat;
import com.concertreservation.domain.model.ConcertDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByConcertDateAndIsReservedFalse(ConcertDate concertDate);

    Optional<Seat> findByConcertDateAndSeatNumber(ConcertDate concertDate, int seatNumber);

    List<Seat> findAllByIsReservedTrue();
}



