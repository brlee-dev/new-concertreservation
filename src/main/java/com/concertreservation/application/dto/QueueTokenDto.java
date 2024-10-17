package com.concertreservation.application.dto;

public class QueueTokenDto {
    private Long queueId;
    private String uuid;
    private int position;
    private int remainingTime;

    public QueueTokenDto(Long queueId, String userId, int position, int remainingTime) {
        this.queueId = queueId;
        this.uuid = userId;
        this.position = position;
        this.remainingTime = remainingTime;
    }

    public Long getQueueId() {
        return queueId;
    }

    public String getUserId() {
        return uuid;
    }

    public int getPosition() {
        return position;
    }

    public int getRemainingTime() {
        return remainingTime;
    }
}
