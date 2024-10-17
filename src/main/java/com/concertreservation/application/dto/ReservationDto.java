package com.concertreservation.application.dto;

public class ReservationDto {
    private String userId;
    private String date;
    private int seatNumber;

    public ReservationDto(String userId, String date, int seatNumber) {
        this.userId = userId;
        this.date = date;
        this.seatNumber = seatNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
