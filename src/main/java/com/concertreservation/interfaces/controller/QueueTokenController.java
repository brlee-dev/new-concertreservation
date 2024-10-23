package com.concertreservation.interfaces.controller;

import com.concertreservation.application.service.QueueTokenService;
import com.concertreservation.domain.model.QueueToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/queue")
public class QueueTokenController {

    private final QueueTokenService queueTokenService;

    public QueueTokenController(QueueTokenService queueTokenService) {
        this.queueTokenService = queueTokenService;
    }

    @PostMapping("/issue-token")
    public QueueToken issueToken(@RequestParam UUID userId) {
        return queueTokenService.issueToken(userId);
    }

    @GetMapping("/status/{tokenId}")
    public QueueToken getQueueStatus(@PathVariable UUID tokenId) {
        return queueTokenService.getQueueStatus(tokenId);
    }
}
