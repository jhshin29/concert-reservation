package com.hhplus.concert_reservation.core.domain.concert.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Comment("콘서트 스케줄 리스트")
public class ConcertSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("PK")
    private Long id;

    @Column(nullable = false)
    @Comment("콘서트 ID")
    private Long concertId;

    @Column(nullable = false)
    @Comment("콘서트 날짜")
    private LocalDate openDate;

    @Column(nullable = false)
    @Comment("콘서트 시작 시간")
    private LocalDateTime startAt;

    @Column(nullable = false)
    @Comment("콘서트 종료 시간")
    private LocalDateTime endAt;

    @Column(nullable = false)
    @Comment("전체 좌석 수")
    private int totalSeats;

    @Column(nullable = false)
    @Comment("잔여 좌석 수")
    private int remainingSeats;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Comment("매진 여부")
    private boolean isSoldOut;

    @Column(nullable = false)
    @Comment("생성일시")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Comment("삭제여부")
    private boolean isDelete;

}
