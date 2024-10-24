package com.concertreservation.interfaces.controller;

import com.concertreservation.application.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public String processPayment(@RequestParam String userId, @RequestParam Long seatId, @RequestParam String tokenId) {
        paymentService.processPayment(userId, seatId, tokenId);
        return "결제가 완료되었습니다.";
    }
}