package com.concertreservation.exception;

public class GlobalExceptionHandler {

    private String code;
    private String message;

    // 생성자
    public GlobalExceptionHandler(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getter 및 Setter
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

