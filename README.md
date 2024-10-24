# 배포 환경 구성
 - 서버 : NHN클라우드 인스턴스 2Core 8GB
 - OS 버전 : Rocky Linux 8.10
 - 공인 IP : 133.186.221.141
![image](https://github.com/user-attachments/assets/1b5ddc2c-b0da-4ffe-b2b3-0d7133108eb3)

 - springboot 서비스 확인
![image](https://github.com/user-attachments/assets/9043f41f-937e-4e21-91ff-a20441e92ed3)

  
 - MySQL 버전 : 8.0.36
![image](https://github.com/user-attachments/assets/160877ae-4b4f-4194-a65e-9a757e34aee8)


# 테스트 시나리오
 1. 사용자 토큰 발급
 2. 잔액 충전
 3. 잔액 조회
 4. 예약 가능한 날짜 목록 조회
 5. 특정 날짜의 예약 가능한 좌석 조회
 6. 좌석 예약
 7. 좌석 결제
 8. 에러 상황 테스트

## 1. 사용자 토큰 발급
설명
 - 사용자가 시스템에 접속하여 토큰을 발급받습니다.

### 엔드포인트
- `POST /api/queue/issue-token`
![image](https://github.com/user-attachments/assets/8ac3d6f9-f7b9-4883-9993-5a4dffae6a4f)

## 2. 잔액 충전 API
설명
 - 사용자가 자신의 계정에 잔액을 충전합니다.

### 엔드포인트
 - `POST /api/balance/recharge`
![image](https://github.com/user-attachments/assets/9f319769-5cd7-4076-8944-021af758b0aa)
![image](https://github.com/user-attachments/assets/7ecd6adf-7d2a-475a-8d5b-85a590325ed3)

## 3. 잔액 조회 API
설명
 - 사용자가 자신의 잔액을 조회합니다.

### 엔드포인트
GET /api/balance/get
![image](https://github.com/user-attachments/assets/23a72fc2-c9d0-4e80-944f-93eb0b7cf57f)

## 4. 예약 가능한 날짜 목록 조회 API
설명
 - 사용자가 예약 가능한 공연 날짜를 조회합니다.

### 엔드포인트
GET /api/reservations/dates
