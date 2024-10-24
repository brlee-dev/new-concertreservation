package com.concertreservation.interfaces.controller;

import com.concertreservation.application.service.BalanceService;
import com.concertreservation.application.service.QueueTokenService;
import com.concertreservation.domain.model.QueueToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final BalanceService balanceService;
    private final QueueTokenService queueTokenService;

    public BalanceController(BalanceService balanceService, QueueTokenService queueTokenService) {
        this.balanceService = balanceService;
        this.queueTokenService = queueTokenService;
    }

    // 잔액 충전
    @PostMapping("/recharge")
    public String rechargeBalance(@RequestParam String userId, @RequestParam double amount, @RequestParam String tokenId) {
        // 토큰 유효성 검사 및 사용자 정보 획득
        QueueToken token = queueTokenService.getQueueStatus(tokenId);
        String tokenUserId = token.getUser().getId();

        // 요청된 userId와 토큰의 userId가 일치하는지 확인
        if (!tokenUserId.equals(userId)) {
            throw new RuntimeException("인증된 사용자와 요청된 사용자 ID가 일치하지 않습니다.");
        }

        balanceService.rechargeBalance(userId, amount);
        return "잔액이 충전되었습니다.";
    }

    // 잔액 조회
    @GetMapping("/get")
    public double getBalance(@RequestParam String userId, @RequestParam String tokenId) {
        // 토큰 유효성 검사 및 사용자 정보 획득
        QueueToken token = queueTokenService.getQueueStatus(tokenId);
        String tokenUserId = token.getUser().getId();

        // 요청된 userId와 토큰의 userId가 일치하는지 확인
        if (!tokenUserId.equals(userId)) {
            throw new RuntimeException("인증된 사용자와 요청된 사용자 ID가 일치하지 않습니다.");
        }

        return balanceService.getBalance(userId);
    }
}





