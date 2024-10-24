package com.concertreservation.application.service;

import com.concertreservation.domain.model.QueueToken;
import com.concertreservation.domain.model.User;
import com.concertreservation.domain.repository.QueueTokenRepository;
import com.concertreservation.exception.BusinessException;
import com.concertreservation.exception.ErrorCode;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class QueueTokenService {

    private final QueueTokenRepository queueTokenRepository;
    private final ConcurrentHashMap<String, QueueToken> activeTokens = new ConcurrentHashMap<>();

    public QueueTokenService(QueueTokenRepository queueTokenRepository) {
        this.queueTokenRepository = queueTokenRepository;
    }

    public QueueToken issueToken(User user) {
        QueueToken token = new QueueToken(user);
        activeTokens.put(token.getTokenId(), token);
        return queueTokenRepository.save(token);
    }

    public QueueToken getQueueStatus(String tokenId) {
        QueueToken token = activeTokens.get(tokenId);
        if (token == null || token.isExpired()) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID);
        }
        return token;
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void updateExpiredTokens() {
        System.out.println("스케줄러 실행 중: 만료된 토큰을 업데이트합니다.");
        activeTokens.forEach((tokenId, token) -> {
            if (token.isTimeExpired()) {
                token.setStatus("expired");
                queueTokenRepository.save(token); // 상태 업데이트
                activeTokens.remove(tokenId);
                System.out.println("토큰 만료됨: " + tokenId);
            }
        });
    }
}


