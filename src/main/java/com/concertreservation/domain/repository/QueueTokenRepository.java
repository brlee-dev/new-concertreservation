package com.concertreservation.domain.repository;

import com.concertreservation.domain.model.QueueToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QueueTokenRepository extends JpaRepository<QueueToken, String> {
    List<QueueToken> findByStatusAndExpiresAtBefore(String status, LocalDateTime dateTime);
}

