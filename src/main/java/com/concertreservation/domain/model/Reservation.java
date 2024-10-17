package com.concertreservation.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "is_temporary")
    private boolean isTemporary;

    protected Reservation() {
    }

    public Reservation(User user, LocalDate date, int seatNumber, boolean isTemporary) {
        this.user = user;
        this.date = date;
        this.seatNumber = seatNumber;
        this.isTemporary = isTemporary;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
