package com.hhplus.concert_reservation.core.domain.user.entities;

import io.jsonwebtoken.Jwts;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long balance;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isDeleted;

    public static long extractUserIdFromJwt(String token) {
        return Jwts.parserBuilder()
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .get("userId", Long.class);
    }

    public void chargeBalance(long balance) {
        if (0 >= balance) {
            throw new IllegalArgumentException("금액을 1원 이상으로 설정해주세요.");
        }
        this.balance += balance;
    }

    public void checkConcertAmount(long seatAmount) {
        if (this.balance < seatAmount) {
            throw new IllegalArgumentException("결제할 잔액이 부족합니다.");
        }
        this.balance -= balance;
    }
}
