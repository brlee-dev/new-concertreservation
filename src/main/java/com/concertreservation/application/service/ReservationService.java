package com.concertreservation.application.service;

import com.concertreservation.domain.model.ConcertDate;
import com.concertreservation.domain.model.Seat;
import com.concertreservation.domain.model.User;
import com.concertreservation.domain.repository.ConcertDateRepository;
import com.concertreservation.domain.repository.SeatRepository;
import com.concertreservation.exception.BusinessException;
import com.concertreservation.exception.ErrorCode;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ConcertDateRepository concertDateRepository;
    private final SeatRepository seatRepository;

    public ReservationService(ConcertDateRepository concertDateRepository, SeatRepository seatRepository) {
        this.concertDateRepository = concertDateRepository;
        this.seatRepository = seatRepository;
    }

    // 예약 가능한 날짜 목록 조회
    public List<ConcertDate> getAvailableDates() {
        return concertDateRepository.findAll();
    }

    // 특정 날짜의 예약 가능한 좌석 목록 조회
    public List<Seat> getAvailableSeats(LocalDate date) {
        ConcertDate concertDate = concertDateRepository.findById(date)
                .orElseThrow(() -> new BusinessException(ErrorCode.CONCERT_NOT_FOUND));
        return seatRepository.findByConcertDateAndIsReservedFalse(concertDate);
    }

    // 좌석 예약
    @Transactional
    public void reserveSeat(LocalDate date, int seatNumber, User user) {
        ConcertDate concertDate = concertDateRepository.findById(date)
                .orElseThrow(() -> new BusinessException(ErrorCode.CONCERT_NOT_FOUND));

        Seat seat = seatRepository.findByConcertDateAndSeatNumber(concertDate, seatNumber)
                .orElseThrow(() -> new BusinessException(ErrorCode.SEAT_NOT_FOUND));

        if (!seat.isAvailable()) {
            throw new BusinessException(ErrorCode.SEAT_NOT_AVAILABLE);
        }

        seat.reserve(user, 5); // 5분간 임시 배정
        seatRepository.save(seat);
    }

    // 예약 만료 처리 스케줄러
    @Scheduled(fixedDelay = 60000) // 1분마다 실행
    @Transactional
    public void releaseExpiredReservations() {
        List<Seat> seats = seatRepository.findAllByIsReservedTrue();
        for (Seat seat : seats) {
            if (LocalDateTime.now().isAfter(seat.getExpiresAt())) {
                seat.release();
                seatRepository.save(seat);
            }
        }
    }
}





