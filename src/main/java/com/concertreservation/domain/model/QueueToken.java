package com.concertreservation.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class QueueToken {

    @Id
    private UUID tokenId;
    private UUID userId;
    private int queuePosition;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    public QueueToken() {
    }

    public QueueToken(UUID userId) {
        this.tokenId = UUID.randomUUID();
        this.userId = userId;
        this.queuePosition = calculateQueuePosition();
        this.issuedAt = LocalDateTime.now();
        this.expiresAt = this.issuedAt.plusMinutes(5); // 5분 유효시간 설정
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiresAt);
    }

    private int calculateQueuePosition() {
        // 실제 대기열 로직 구현
        return 0; // 예시 값
    }

    // Getters and Setters
    public UUID getTokenId() {
        return tokenId;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getQueuePosition() {
        return queuePosition;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
