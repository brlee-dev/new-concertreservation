package com.concertreservation.domain.repository;

import com.concertreservation.domain.model.ConcertDate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface ConcertDateRepository extends JpaRepository<ConcertDate, LocalDate> {
}

