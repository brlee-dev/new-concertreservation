package com.concertreservation.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class QueueToken {

    @Id
    private String tokenId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime issuedAt; // 토큰 발급 시간
    private LocalDateTime expiresAt; // 토큰 만료 시간

    private String status; // 토큰 상태 ('valid', 'expired' 등)

    public QueueToken() {
    }

    public QueueToken(User user) {
        this.tokenId = UUID.randomUUID().toString();
        this.user = user;
        this.issuedAt = LocalDateTime.now();
        this.expiresAt = this.issuedAt.plusMinutes(5); // 토큰 유효 시간 5분
        this.status = "valid"; // 초기 상태 설정
    }

    public boolean isExpired() {
        return "expired".equals(this.status);
    }

    public boolean isTimeExpired() {
        return LocalDateTime.now().isAfter(this.expiresAt);
    }

    // Getters and Setters
    public String getTokenId() {
        return tokenId;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


