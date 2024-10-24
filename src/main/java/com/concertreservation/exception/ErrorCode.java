package com.concertreservation.exception;

public enum ErrorCode {
    // 공통 에러 코드
    INVALID_INPUT_VALUE("C001", "입력 값이 유효하지 않습니다."),
    METHOD_NOT_ALLOWED("C002", "허용되지 않은 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR("C003", "서버 내부 에러가 발생했습니다."),

    // 사용자 관련 에러 코드
    USER_NOT_FOUND("U001", "사용자를 찾을 수 없습니다."),
    INSUFFICIENT_BALANCE("U002", "잔액이 부족합니다."),
    USER_ID_MISMATCH("U003", "인증된 사용자와 요청된 사용자 ID가 일치하지 않습니다."),

    // 토큰 관련 에러 코드
    TOKEN_NOT_FOUND("T001", "토큰을 찾을 수 없습니다."),
    TOKEN_EXPIRED("T002", "토큰이 만료되었습니다."),
    TOKEN_INVALID("T003", "토큰이 만료되었거나 존재하지 않습니다."),

    // 좌석 관련 에러 코드
    SEAT_NOT_FOUND("S001", "좌석을 찾을 수 없습니다."),
    SEAT_ALREADY_RESERVED("S002", "이미 예약된 좌석입니다."),
    SEAT_NOT_AVAILABLE("S003", "해당 좌석은 예약할 수 없습니다."),
    SEAT_ALREADY_SOLD("S004", "이미 판매된 좌석입니다."),

    // 공연 관련 에러 코드
    CONCERT_NOT_FOUND("CON004", "해당 날짜의 공연이 존재하지 않습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getter 메서드
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
