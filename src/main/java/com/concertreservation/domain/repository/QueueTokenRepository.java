package com.concertreservation.domain.repository;

import com.concertreservation.domain.model.QueueToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QueueTokenRepository extends JpaRepository<QueueToken, UUID> {
    Optional<QueueToken> findById(UUID tokenId);
}
