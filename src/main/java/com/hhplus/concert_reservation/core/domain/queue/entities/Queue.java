package com.hhplus.concert_reservation.core.domain.queue.entities;

import io.jsonwebtoken.Jwts;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("PK")
    private Long id;

    @Column(nullable = false)
    @Comment("유저 ID")
    private long userId;

    @Column(nullable = false)
    @Comment("대기열 토큰")
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("대기열 상태 - WAITING, PROGRESS, DONE, EXPIRED")
    private QueueStatus status;

    @Column(nullable = false)
    @Comment("대기열 진입 시작")
    private LocalDateTime enteredAt;

    @Comment("대기열 만료 시간")
    private LocalDateTime expiredAt;

    public Queue(long userId, String token) {
        this.userId = userId;
        this.token = token;
        this.status = QueueStatus.WAITING;
        this.enteredAt = LocalDateTime.now();
        this.expiredAt = LocalDateTime.now().plusMinutes(5);
    }

    public boolean isTokenValid() {
        if (status == QueueStatus.WAITING || status == QueueStatus.PROGRESS) {
            return expiredAt == null || expiredAt.isAfter(LocalDateTime.now());
        }
        return false;
    }

    private static String generateJwtToken(long userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("token", UUID.randomUUID().toString())
                .claim("enteredAt", new Date())
                .claim("expiredAt", new Date(System.currentTimeMillis() + 300000))
                .compact();
    }

    public static Queue enterQueue(Queue existingQueue, long userId) {
        if (existingQueue != null && existingQueue.isTokenValid()) {
            return existingQueue;
        }
        return new Queue(userId, generateJwtToken(userId));
    }

    public void checkWaitingQueue(List<Queue> queueList) {
        if (queueList.size() <= 30 && this.getStatus() == QueueStatus.WAITING) {

            LocalDateTime expireAt = LocalDateTime.now().plusMinutes(10);

            this.status = QueueStatus.WAITING;
            this.enteredAt = expireAt;
        } else {
            if (this.getStatus() == QueueStatus.EXPIRED) {
                throw new IllegalArgumentException("대기열이 만료되었습니다.");
            }
        }
    }
}
