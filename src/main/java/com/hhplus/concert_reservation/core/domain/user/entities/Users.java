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

    public Users(long balance, LocalDateTime createdAt, boolean isDeleted) {
        this.balance = balance;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public static long extractUserIdFromJwt(String token) {
        return Jwts.parserBuilder()
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .get("userId", Long.class);
    }

    public long chargeBalance(long balance) {
        return this.balance += balance;
    }
}
