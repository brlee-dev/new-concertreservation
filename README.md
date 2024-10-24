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
 - `GET /api/balance/get`
![image](https://github.com/user-attachments/assets/23a72fc2-c9d0-4e80-944f-93eb0b7cf57f)

## 4. 예약 가능한 날짜 목록 조회 API
설명
 - 사용자가 예약 가능한 공연 날짜를 조회합니다.

### 엔드포인트
 - `GET /api/reservations/dates`
![image](https://github.com/user-attachments/assets/c4d8ed2d-28f9-46f5-9fcf-4cb16b5a6fdd)
![image](https://github.com/user-attachments/assets/dcf85d62-b04f-407b-be08-bfc59f0d1f8f)

## 5. 특정 날짜의 예약 가능한 좌석 조회 API
설명
 - 사용자가 특정 날짜의 예약 가능한 좌석을 조회합니다.

### 엔드포인트
 - `GET /api/reservations/seats`
![image](https://github.com/user-attachments/assets/2dd53491-41dd-4f36-aa20-f08c7ff52863)
![image](https://github.com/user-attachments/assets/ee70dfec-09fd-4483-a0a5-e65f334adcec)

## 6. 좌석 예약 API
설명
 - 사용자가 특정 좌석을 예약합니다.
 
### 엔드포인트
 - `POST /api/reservations/reserve`
![image](https://github.com/user-attachments/assets/ff9a1d81-435c-4261-b4d0-827f420d91f3)
![image](https://github.com/user-attachments/assets/eb43fbf7-442f-4c78-86e6-4e21b349f959)

## 7. 좌석 결제 API
설명
 - 사용자가 예약한 좌석을 결제합니다.

### 엔드포인트
 - `POST /api/payment/process`
![image](https://github.com/user-attachments/assets/67507dec-0e7c-4ad1-b52c-6cc43fea845a)
![image](https://github.com/user-attachments/assets/85e7f69e-70e7-4ca0-ab12-9524023ea482)

## 8. 몇가지 에러 상황 테스트
### 시나리오
 - 만료된 토큰으로 요청하거나 잔액이 부족한 상태에서 결제를 시도합니다.
 - 
(a) 만료된 토큰으로 요청
설명: 토큰 발급 후 5분이 지난 후 요청합니다.
요청 방법: 위의 요청들과 동일하지만, 만료된 tokenId를 사용합니다.
![image](https://github.com/user-attachments/assets/ae2c275e-2dcd-4d16-886a-12ac28e9a2ef)


(b) 잔액 부족으로 결제 시도
설명: 사용자의 잔액이 좌석 가격보다 적은 상태에서 결제를 시도합니다.
![image](https://github.com/user-attachments/assets/ba38286d-dfd9-4a5a-87f9-1f41b0b8a2e8)
![image](https://github.com/user-attachments/assets/3941050d-2cc2-416e-96a2-3d552f5f877e)

