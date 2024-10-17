package com.concertreservation.application.dto;

public class QueueStatusDto {
    private int position;
    private int remainingTime;

    public QueueStatusDto(int position, int remainingTime) {
        this.position = position;
        this.remainingTime = remainingTime;
    }

    // getter
    public int getPosition() {
        return position;
    }

    public int getRemainingTime() {
        return remainingTime;
    }
}
