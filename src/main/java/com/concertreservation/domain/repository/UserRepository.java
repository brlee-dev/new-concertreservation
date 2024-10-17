package com.concertreservation.domain.repository;

import com.concertreservation.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(String uuid); // String 타입의 UUID 기반 조회 메서드
}
