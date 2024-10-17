package com.concertreservation.application.service;

import com.concertreservation.application.dto.QueueTokenDto;
import com.concertreservation.domain.model.Queue;
import com.concertreservation.domain.model.User;
import com.concertreservation.domain.repository.QueueRepository;
import com.concertreservation.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final UserRepository userRepository;

    public QueueService(QueueRepository queueRepository, UserRepository userRepository) {
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public QueueTokenDto issueQueueToken(String userId) {
        User user = userRepository.findByUuid(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Queue queue = new Queue(user, calculatePosition());
        queueRepository.save(queue);

        return new QueueTokenDto(queue.getQueueId(), user.getUuid(), queue.getPosition(), queue.calculateRemainingTime());
    }

    public QueueTokenDto checkQueueStatus(String userId) {
        Optional<Queue> optionalQueue = queueRepository.findByUser_Uuid(userId);
        
        if (optionalQueue.isEmpty()) {
            throw new IllegalArgumentException("Queue not found");
        }

        Queue queue = optionalQueue.get();
        int remainingTime = queue.calculateRemainingTime();

        if (remainingTime <= 0) {
            queue.setStatus("EXPIRED");
            queueRepository.save(queue);
        }

        return new QueueTokenDto(queue.getQueueId(), userId, queue.getPosition(), remainingTime);
    }

    private int calculatePosition() {
        return (int) queueRepository.count() + 1;
    }
}
