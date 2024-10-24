package com.hhplus.concert_reservation.core.interfaces.api.concert.response;

import com.hhplus.concert_reservation.core.domain.concert.dto.PaymentConcertResult;
import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;
import com.hhplus.concert_reservation.core.domain.reservation.entities.ReservationStatus;

public record PaymentResponse(
        long paymentAmount,
        ReservationStatus seatStatus,
        QueueStatus queueStatus
) {
    public static PaymentResponse of(PaymentConcertResult result) {
        return new PaymentResponse(
                result.paymentAmount(),
                result.seatStatus(),
                result.queueStatus()
        );
    }
}
