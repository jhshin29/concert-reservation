package com.hhplus.concert_reservation.core.domain.reservation.entities;

import com.hhplus.concert_reservation.core.domain.concert.entities.Concert;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSchedule;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSeat;
import com.hhplus.concert_reservation.core.domain.user.entities.Users;
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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("유저 ID")
    private Long userId;

    @Column(nullable = false)
    @Comment("좌석 ID")
    private Long seatId;

    @Column(nullable = false)
    @Comment("콘서트명")
    private String concertTitle;

    @Column(nullable = false)
    @Comment("콘서트 날짜")
    private LocalDate concertOpenDate;

    @Column(nullable = false)
    @Comment("콘서트 시작 시간")
    private LocalDateTime concertStartAt;

    @Column(nullable = false)
    @Comment("콘서트 종료 시간")
    private LocalDateTime concertEndAt;

    @Column(nullable = false)
    @Comment("좌석 금액")
    private long seatAmount;

    @Column(nullable = false)
    @Comment("좌석 번호")
    private int seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("예약 상태 - TEMP_RESERVED, RESERVED, CANCELED")
    private ReservationStatus status;

    @Column(nullable = false)
    @Comment("예약 시간")
    private LocalDateTime reservedAt;

    @Comment("예약 만료 시간")
    private LocalDateTime reservedUntilAt;

    @Column(nullable = false)
    @Comment("생성일시")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Comment("삭제여부")
    private boolean isDelete;

    public Reservation(Long userId, Long seatId, String concertTitle, LocalDate concertOpenDate, LocalDateTime concertStartAt, LocalDateTime concertEndAt, long seatAmount, int seatNumber) {
        this.userId = userId;
        this.seatId = seatId;
        this.concertTitle = concertTitle;
        this.concertOpenDate = concertOpenDate;
        this.concertStartAt = concertStartAt;
        this.concertEndAt = concertEndAt;
        this.seatAmount = seatAmount;
        this.seatNumber = seatNumber;
        this.status = ReservationStatus.TEMP_RESERVED;
        this.reservedAt = LocalDateTime.now();
        this.reservedUntilAt = LocalDateTime.now().plusMinutes(5);
        this.createdAt = LocalDateTime.now();
        this.isDelete = false;
    }

    public static Reservation enterReservation(Users user, Concert concert, ConcertSeat concertSeat, ConcertSchedule concertSchedule) {
        return new Reservation(user.getId(), concertSeat.getId(), concert.getTitle(), concertSchedule.getOpenDate(), concertSchedule.getStartAt(), concertSchedule.getEndAt(), concertSeat.getAmount(), concertSeat.getSeatNumber());
    }

    public void finishReserve() {
        if (this.status == ReservationStatus.TEMP_RESERVED) {
            this.status = ReservationStatus.RESERVED;
        }
    }
}
