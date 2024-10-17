package com.concertreservation.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.Duration;

@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;
    String uuid;
    private LocalDateTime createdAt;
    private int position;
    private String status;

    protected Queue() {
    }

    public Queue(String uuid, int position) {
        this.uuid = uuid;
        this.position = position;
        this.createdAt = LocalDateTime.now();
        this.status = "ACTIVE";
    }

    public int calculateRemainingTime() {
        Duration duration = Duration.between(createdAt, LocalDateTime.now());
        long elapsedSeconds = duration.getSeconds();
        return (int) Math.max(300 - elapsedSeconds, 0); // 5분 기본 설정
    }

    public Long getQueueId() {
        return queueId;
    }
    public String getUuid() {
        return uuid;
    }

    public int getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
