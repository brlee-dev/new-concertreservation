package com.concertreservation.application.service;

import com.concertreservation.domain.model.User;
import com.concertreservation.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceService {

    private final UserRepository userRepository;

    public BalanceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 잔액 충전
    @Transactional
    public void rechargeBalance(String userId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }

    // 잔액 조회
    public double getBalance(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return user.getBalance();
    }
}






