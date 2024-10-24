package com.concertreservation.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seatNumber; // 좌석 번호 (1~50)

    @ManyToOne
    @JoinColumn(name = "date")
    private ConcertDate concertDate; // 공연 날짜

    private boolean isReserved; // 예약 여부

    @ManyToOne
    @JoinColumn(name = "reserved_by")
    private User reservedBy; // 임시 배정된 사용자

    private LocalDateTime reservedAt; // 예약 시간

    private LocalDateTime expiresAt; // 예약 만료 시간

    // 생성자, getters, setters

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    // 필요하다면 setter 메서드도 추가
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isAvailable() {
        if (isReserved && LocalDateTime.now().isBefore(expiresAt)) {
            return false;
        }
        return true;
    }

    public void reserve(User user, int minutes) {
        this.isReserved = true;
        this.reservedBy = user;
        this.reservedAt = LocalDateTime.now();
        this.expiresAt = reservedAt.plusMinutes(minutes);
    }

    public void release() {
        this.isReserved = false;
        this.reservedBy = null;
        this.reservedAt = null;
        this.expiresAt = null;
    }

    // 필드 추가
    private boolean isSold; // 판매 여부
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner; // 좌석 소유자
    private double price; // 좌석 가격

    // Getter 및 Setter 추가
    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}


