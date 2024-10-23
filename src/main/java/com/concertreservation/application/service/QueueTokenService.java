package com.concertreservation.application.service;

import com.concertreservation.domain.model.QueueToken;
import com.concertreservation.domain.repository.QueueTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QueueTokenService {

    private final QueueTokenRepository queueTokenRepository;
    private final ConcurrentHashMap<UUID, QueueToken> activeTokens = new ConcurrentHashMap<>();

    public QueueTokenService(QueueTokenRepository queueTokenRepository) {
        this.queueTokenRepository = queueTokenRepository;
    }

    public QueueToken issueToken(UUID userId) {
        QueueToken token = new QueueToken(userId);
        activeTokens.put(token.getTokenId(), token);
        return queueTokenRepository.save(token);
    }

    public QueueToken getQueueStatus(UUID tokenId) {
        QueueToken token = activeTokens.get(tokenId);
        if (token == null || token.isExpired()) {
            throw new RuntimeException("Token expired or not found");
        }
        return token;
    }

    @Scheduled(fixedDelay = 60000) // 매 1분마다 스케줄링
    public void cleanUpExpiredTokens() {
        activeTokens.forEach((tokenId, token) -> {
            if (token.isExpired()) {
                activeTokens.remove(tokenId);
                queueTokenRepository.delete(token); // DB에서도 제거
            }
        });
    }
}
