package com.concertreservation.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class ConcertDate {

    @Id
    private LocalDate date; // 공연 날짜

    public ConcertDate() {
    }

    public ConcertDate(LocalDate date) {
        this.date = date;
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
