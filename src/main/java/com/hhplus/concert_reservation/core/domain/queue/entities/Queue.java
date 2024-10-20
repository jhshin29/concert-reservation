package com.hhplus.concert_reservation.core.domain.queue.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

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
}
