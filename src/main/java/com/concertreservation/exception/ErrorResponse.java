package com.concertreservation.exception;

public class ErrorResponse {

    private String code;
    private String message;

    // 생성자
    public ErrorResponse(String code, String message) {
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
