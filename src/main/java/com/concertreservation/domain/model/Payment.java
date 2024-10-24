package com.concertreservation.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount; // 결제 금액

    private LocalDateTime paymentTime; // 결제 시간

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 결제한 사용자

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat; // 결제한 좌석

    public Payment() {
    }

    public Payment(double amount, User user, Seat seat) {
        this.amount = amount;
        this.user = user;
        this.seat = seat;
        this.paymentTime = LocalDateTime.now();
    }

    // Getters and Setters
    // ...
}
