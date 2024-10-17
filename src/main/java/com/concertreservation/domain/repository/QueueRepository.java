package com.concertreservation.domain.repository;

import com.concertreservation.domain.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface QueueRepository extends JpaRepository<Queue, Long> {
    Optional<Queue> findByUser_UserId(String userId);
}
