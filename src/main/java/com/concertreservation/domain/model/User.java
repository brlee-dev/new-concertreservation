package com.concertreservation.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String userId;

    private String uuid;

    // 기본 생성자
    protected User() {
    }

    public User(String uuid) {
        this.uuid = uuid;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUuid() {
        return uuid;
    }
}
