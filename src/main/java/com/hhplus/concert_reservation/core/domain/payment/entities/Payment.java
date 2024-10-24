package com.hhplus.concert_reservation.core.domain.payment.entities;

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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("PK")
    private Long id;

    @Column(nullable = false)
    @Comment("유저 ID")
    private Long userId;

    @Column(nullable = false)
    @Comment("예약 ID")
    private Long reservationId;

    @Column(nullable = false)
    @Comment("결제금액")
    private long price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("결제 상태 - PROGRESS, DONE, CANCELED")
    private PaymentStatus status;

    @Column(nullable = false)
    @Comment("생성일시")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Comment("삭제여부")
    private boolean isDelete;

    public Payment(Long userId, Long reservationId, long price, PaymentStatus status) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.price = price;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.isDelete = false;
    }

    public static Payment enterPayment(long userId, long reservationId, long price, PaymentStatus status) {
        return new Payment(userId, reservationId, price, status);
    }

    public void finishPayment() {
        this.status = PaymentStatus.DONE;
    }
}
