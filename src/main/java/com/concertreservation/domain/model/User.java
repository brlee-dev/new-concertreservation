package com.concertreservation.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class User {

    @Id
    private String id;

    private double balance; // 잔액 필드 추가

    public User() {
        this.id = UUID.randomUUID().toString();
        this.balance = 0.0; // 초기 잔액은 0으로 설정
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}