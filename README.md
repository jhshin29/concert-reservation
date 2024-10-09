# 콘서트 예약 서비스 Docs

# MileStones
### 마일스톤 링크
[View the Full Roadmap](https://github.com/users/jhshin29/projects/1/views/1)
***
### 1주차 마일스톤
![1주차 마일스톤](src/main/resources/static/1주차%20마일스톤.png)
### 2-3주차 마일스톤
![2-3주차 마일스톤](src/main/resources/static/2_3주차%20마일스톤.png)
***
# Flowchart
## 예약 flow
```mermaid
flowchart TD
%% Nodes
    A(유저 토큰 발급)
    A1(대기열 검증 실패)
    C(예약 가능 날짜 / 좌석 조회)
    C1(예약 불가)
    D(좌석 예약 요청)
    E(결제)
    E1(좌석 예약 실패)
    F(좌석 배정)

%% Edge connections between nodes
    A --> |대기열 검증 성공|C --> |성공|D --> |5분간 좌석 임시배정|E --> |성공|F
    A --> A1
    C --> |없음|C1
    E --> |임시배정 만료 or 충전액 부족|E1
```

## 예약 가능 날짜 / 좌석 조회 flow
```mermaid
flowchart TD
%% Nodes
    A(유저 토큰 발급)
    A1(대기열 검증 실패)
    C(예약 가능 날짜 / 좌석 조회)
    C1(조회 실패ㅣ 빈 배열 리턴)
    D(예약 가능 리스트 리턴)

%% Edge connections between nodes
    A --> |대기열 검증 성공|C --> |성공|D
    A --> A1
    C --> |없음|C1
```

## 잔액 충전 / 조회 flow
```mermaid
flowchart TD
%% Nodes
    A(유저 토큰 발급)
    A1(요청 실패)
    B(잔액 충전 요청)
    B1(잔액 조회 요청)
    C(잔액 충전)
    D(잔액 조회 성공)
    D1(잔액 조회 실패)
    E(잔액 충전 불가)


%% Edge connections between nodes
    A --> |검증 및 사용자 식별 성공|B --> |성공|C
    A --> |검증 실패|A1
    A --> |검증 및 사용자 식별 성공|B1 --> |성공|D
    B --> |에러 발생|E
    B1 --> |에러 발생|D1
```

# Sequence Diagram

## 유저 대기열 토큰 발급 API

```mermaid
sequenceDiagram
    autonumber
    actor user as 사용자
    participant server as API 서버
    participant waitingSystem as 대기열 시스템
    user ->> server: 대기열 토큰 발급 요청
    server ->> waitingSystem: 대기열 토큰 발급 요청
    alt 유저가 대기열에 이미 존재
        waitingSystem -->> server: 기존 대기열 토큰 반환
    else 대기열에 새롭게 들어온 경우
        waitingSystem -->> server: 신규 대기열 토큰 생성 및 반환
    end
    server -->> user: 대기열 토큰 반환
```

## 나의 대기번호 조회 API (토큰 검증) - Polling

```mermaid
sequenceDiagram
    autonumber
    actor user as 사용자
    participant server as API 서버
    participant waitingSystem as 대기열 시스템
    
    loop 5초마다 확인
        user ->> server: 대기열 순번 확인 요청
        Note over user, server: Header에 토큰 추가
        server ->> waitingSystem: 대기열 순번 확인 요청
        alt 아직 순번이 남은 경우
            waitingSystem -->> server: 현재 대기 순번 리턴
            server -->> user: 현재 대기 순번 응답<br>(토큰 정보 업데이트)
        else 대기열 통과
            waitingSystem -->> server: 예약 가능 상태 리턴
            server -->> user: 예약 가능 응답
        end
    end
```

## 예약 가능 날짜 조회 API

```mermaid
sequenceDiagram
    autonumber
    actor user as 사용자
    participant server as API 서버
    participant authSystem as 토큰 인증 시스템
    participant availableDateList as 콘서트 날짜 리스트
    
    user ->>+ server: 예약 가능 날짜<br>조회 요청
    server ->> authSystem: 토큰 검증 요청
    Note over server, authSystem: Header에 토큰 존재
    alt 대기열 미통과
        authSystem -->> server: API 사용 불가 상태 리턴
        server -->> user: 날짜 조회 불가 응답 (대기중)
    else 대기열 통과
        authSystem -->> server: API 사용 가능 상태 리턴
        server ->> availableDateList: 예약 가능 날짜 리스트 요청
        alt 가능 날짜 미존재
            availableDateList -->> server: 빈 배열 리턴
        else 가능 날짜 존재
            availableDateList -->> server: 예약 가능 날짜 리스트 리턴
        end
        server -->> user: 반환된 날짜 리스트 리턴
    end
```

## 예약 가능 좌석 조회 API

```mermaid
sequenceDiagram
    autonumber
    actor user as 사용자
    participant server as API 서버
    participant authSystem as 토큰 인증 시스템
    participant availableSeatList as 콘서트 좌석 리스트
    
    user ->>+ server: 예약 가능 좌석<br>조회 요청 (w.날짜정보)
    server ->> authSystem: 토큰 검증 요청
    Note over server, authSystem: Header에 토큰 존재
    alt 대기열 미통과
        authSystem -->> server: API 사용 불가 상태 리턴
        server -->> user: 좌석 조회 불가 응답 (대기중)
    else 대기열 통과
        authSystem -->> server: API 사용 가능 상태 리턴
        server ->> availableSeatList: 예약 가능 좌석 리스트 요청 (w.날짜정보)
        alt 가능 좌석 미존재
            availableSeatList -->> server: 빈 배열 리턴
        else 가능 좌석 존재
            availableSeatList -->> server: 예약 가능 좌석 리스트 리턴
        end
        server -->> user: 반환된 좌석 리스트 리턴
    end
```

## 좌석 예약 요청 API

```mermaid
sequenceDiagram
    autonumber
    actor user as 사용자
    participant server as API 서버
    participant authSystem as 토큰 인증 시스템
    participant tempSeat as 임시 배정 좌석

    user ->>+ server: 좌석 예약 요청 (w.날짜, 좌석 정보)
    server ->> authSystem: 토큰 검증 요청
    Note over server, authSystem: Header에 토큰 존재
    alt 대기열 미통과
        authSystem -->> server: API 사용 불가 상태 리턴
        server -->> user: 좌석 예약 불가 응답 (대기중)
    else 대기열 통과
        authSystem -->> server: API 사용 가능 상태 리턴
        server ->> tempSeat: 5분간 좌석 임시 배정 요청
        alt 임시 배정 실패
            tempSeat -->> server: 좌석 없음 상태 리턴
            server -->> user: 좌석 예약 요청 실패 응답
        else 임시 배정 완료
            tempSeat -->> server: 임시 배정 성공 상태 리턴
            server -->> user: 좌석 예약 요청 성공 응답
        end
    end
```
## 잔액 조회 API

```mermaid
sequenceDiagram
    autonumber
    actor user as 사용자
    participant server as API 서버
    participant authSystem as 토큰 인증 시스템
    participant balance as 잔액 정보

    user ->>+ server: 유저 잔액 조회 요청<br>(w. 사용자 식별자)
    server ->> authSystem: 토큰 검증 요청
    Note over server, authSystem: Header에 토큰 존재
    alt 대기열 미통과
        authSystem -->> server: API 사용 불가 상태 리턴
        server -->> user: 잔액 조회 불가 응답 (대기중)
    else 대기열 통과
        authSystem -->> server: API 사용 가능 상태 리턴
        server ->> balance: 유저 잔액 조회 요청<br>(w. 사용자 식별자)
        balance -->> server: 유저 잔액 리턴
        server -->> user: 유저 잔액 정보 응답
end
```

## 잔액 충전 API

```mermaid
sequenceDiagram
    autonumber
    actor user as 사용자
    participant server as API 서버
    participant authSystem as 토큰 인증 시스템
    participant balance as 잔액 정보

    user ->>+ server: 유저 잔액 충전 요청<br>(w. 사용자 식별자, 충전 금액)
    server ->> authSystem: 토큰 검증 요청
    Note over server, authSystem: Header에 토큰 존재
    alt 대기열 미통과
        authSystem -->> server: API 사용 불가 상태 리턴
        server -->> user: 잔액 충전 불가 응답 (대기중)
    else 대기열 통과
        authSystem -->> server: API 사용 가능 상태 리턴
        server ->> balance: 유저 잔액 충전 요청<br>(w. 사용자 식별자, 충전 금액)
        alt 충전 실패
            balance -->> server: 충전 실패 상태 리턴<br>Error: 충전 요청액 마이너스 금액,<br>충전후 잔액 마이너스 금액 등 
            server -->> user: 잔액 충전 실패 응답
        else 충전 성공
            balance ->> balance: 잔액 업데이트
            balance -->> server: 충전 성공 상태 리턴
            server -->> user: 잔액 충전 성공 응답
        end
    end
```
## 결제 API

```mermaid
sequenceDiagram
    autonumber
    actor user as 사용자
    participant server as API 서버
    participant authSystem as 토큰 인증 시스템
    participant balance as 잔액 정보
    participant tempSeat as 임시 좌석 정보
    participant payment as 결제 정보
    participant scheduler as 스케줄러

    user ->>+ server: 결제 요청 (w.사용자 식별자, 임시 좌석 정보)
    server ->> authSystem: 토큰 검증 요청
    Note over server, authSystem: Header에 토큰 존재
    alt 대기열 미통과
        authSystem -->> server: API 사용 불가 상태 리턴
        server -->> user: 결제 실패 응답 (대기중)
    else 대기열 통과
        authSystem -->> server: API 사용 가능 상태 리턴
        server ->> balance: 유저 잔액 조회
        alt 잔액 부족
            balance -->> server: 잔액 부족 상태 리턴
            server -->> user: 결제 실패 응답 (잔액 부족)
        else 잔액 충분
            balance -->> server: 결제 가능 상태 리턴
            server ->> tempSeat: 임시 배정 좌석 확인
            alt 임시 배정 좌석 해제
                tempSeat -->> server: 배정 좌석 없음 상태 리턴
                server -->> user: 결제 실패 응답 (임시 좌석 해제)
            else 임시 배정 좌석 존재
                tempSeat -->> server: 배정 좌석 존재 상태 리턴
                server ->> payment: 결제 요청
                alt 결제 실패
                    payment -->> server: 결제 실패 상태 리턴
                else 결제 성공
                    server ->> balance: 잔액 차감
                    server ->> payment: 결제 상태 변경 및 히스토리 적재
                    payment -->> server: 결제 성공 상태 리턴
                    server ->> authSystem: 대기열 토큰 만료
                    server ->> tempSeat: 좌석 배정 완료 처리
                    server -->> user: 결제 및 예약 완료 응답
                end
            end
        end
    end

    rect rgb(100, 80, 200)
        scheduler ->> payment: 1분에 한 번씩 결제 완료 체크
        alt 5분 내 결제 미완료 시 (1분마다 체크)
            payment -->> server: 5분 내 결제 미완료 상태 리턴
            server ->> tempSeat: 임시 좌석 배정 해제 요청
            tempSeat -->> server: 임시 좌석 배정 해제 완료 리턴
        end
    end
```