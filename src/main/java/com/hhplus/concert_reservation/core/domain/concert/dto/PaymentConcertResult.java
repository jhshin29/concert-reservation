package com.hhplus.concert_reservation.core.domain.concert.dto;

import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;
import com.hhplus.concert_reservation.core.domain.reservation.entities.ReservationStatus;

public record PaymentConcertResult(
        long paymentAmount,
        ReservationStatus seatStatus,
        QueueStatus queueStatus
) {
}
