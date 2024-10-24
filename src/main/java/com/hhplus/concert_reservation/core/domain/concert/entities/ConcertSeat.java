package com.hhplus.concert_reservation.core.domain.concert.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Comment("콘서트 좌석 리스트")
public class ConcertSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("PK")
    private Long id;

    @Column(nullable = false)
    @Comment("콘서트 스케줄 ID")
    private Long concertScheduleId;

    @Column(nullable = false)
    @Comment("좌석 금액")
    private long amount;

    @Column(nullable = false)
    @Comment("좌석 번호")
    private int seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("좌석 상태 - AVAILABLE, TEMP_RESERVED, RESERVED")
    private SeatStatus seatStatus;

    @Comment("임시 예약 만료 시간")
    private LocalDateTime reservedUntilAt;

    @Column(nullable = false)
    @Comment("생성일시")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Comment("삭제여부")
    private boolean isDelete;

    public void isReserveCheck() {
        if (seatStatus != SeatStatus.AVAILABLE) {
            throw new IllegalArgumentException("해당 좌석은 예약할 수 없는 상태 입니다.");
        } else {
            seatStatus = SeatStatus.TEMP_RESERVED;
        }
    }

    public void finishSeatReserve() {
        this.seatStatus = SeatStatus.RESERVED;
    }
}
