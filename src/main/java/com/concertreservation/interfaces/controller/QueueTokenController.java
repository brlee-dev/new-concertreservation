package com.concertreservation.interfaces.controller;

import com.concertreservation.application.service.QueueTokenService;
import com.concertreservation.domain.model.QueueToken;
import com.concertreservation.domain.model.User;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/queue")
public class QueueTokenController {

    private final QueueTokenService queueTokenService;

    public QueueTokenController(QueueTokenService queueTokenService) {
        this.queueTokenService = queueTokenService;
    }

    @PostMapping("/issue-token")
    public QueueToken issueToken(@RequestBody User user) {
        return queueTokenService.issueToken(user);
    }

    @GetMapping("/status/{tokenId}")
    public QueueToken getQueueStatus(@PathVariable String tokenId) {
        return queueTokenService.getQueueStatus(tokenId);
    }
}
