package com.concertreservation.infrastructure.persistence;

import com.concertreservation.domain.model.QueueToken;
import com.concertreservation.domain.repository.QueueTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaQueueTokenRepository extends JpaRepository<QueueToken, UUID>, QueueTokenRepository {
}
