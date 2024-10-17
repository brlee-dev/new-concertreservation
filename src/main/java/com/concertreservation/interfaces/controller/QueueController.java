package com.concertreservation.interfaces.controller;

import com.concertreservation.application.dto.QueueTokenDto;
import com.concertreservation.application.service.QueueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/issue")
    public QueueTokenDto issueToken() {
        return queueService.issueQueueToken();
    }
}
