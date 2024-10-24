package com.concertreservation.application.service;

import com.concertreservation.domain.model.*;
import com.concertreservation.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final QueueTokenRepository queueTokenRepository;

    public PaymentService(PaymentRepository paymentRepository, SeatRepository seatRepository,
                          UserRepository userRepository, QueueTokenRepository queueTokenRepository) {
        this.paymentRepository = paymentRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.queueTokenRepository = queueTokenRepository;
    }

    @Transactional
    public void processPayment(String userId, Long seatId, String tokenId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("좌석을 찾을 수 없습니다."));

        QueueToken token = queueTokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("토큰을 찾을 수 없습니다."));

        // 토큰 만료 여부 확인
        if (token.isExpired()) {
            throw new RuntimeException("토큰이 만료되었습니다.");
        }

        // 좌석이 이미 판매되었는지 확인
        if (seat.isSold()) {
            throw new RuntimeException("이미 판매된 좌석입니다.");
        }

        // 사용자 잔액 확인
        double price = seat.getPrice(); // 좌석 가격
        if (user.getBalance() < price) {
            throw new RuntimeException("잔액이 부족합니다.");
        }

        // 잔액 차감
        user.setBalance(user.getBalance() - price);
        userRepository.save(user);

        // 결제 내역 생성
        Payment payment = new Payment(price, user, seat);
        paymentRepository.save(payment);

        // 좌석 소유권 사용자에게 배정
        seat.setSold(true);
        seat.setOwner(user);
        seatRepository.save(seat);

        // 대기열 토큰 만료 처리
        token.setStatus("expired");
        queueTokenRepository.save(token);
    }
}






