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
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("PK")
    private Long id;

    @Column(nullable = false)
    @Comment("유저 ID")
    private Long userId;

    @Comment("결제 ID")
    private Long paymentId;

    @Column(nullable = false)
    @Comment("금액변경")
    private Long amountChange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("결제 타입 - PAYMENT, REFUND")
    private PaymentType type;

    @Column(nullable = false)
    @Comment("생성일시")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Comment("삭제여부")
    private boolean isDelete = false;

    public PaymentHistory(Long userId, Long amount, PaymentType paymentType, Long paymentId) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.amountChange = amount;
        this.type = paymentType;
        this.createdAt = LocalDateTime.now();
        this.isDelete = false;
    }

    public static PaymentHistory enterPaymentHistory(Long userId, Long amount, PaymentType paymentType, Long paymentId) {
        return new PaymentHistory(userId, amount, paymentType, paymentId);
    }
}
