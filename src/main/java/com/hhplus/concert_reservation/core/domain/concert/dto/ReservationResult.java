package com.hhplus.concert_reservation.core.domain.concert.dto;

import com.hhplus.concert_reservation.core.domain.reservation.entities.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResult(
        long reservationId,
        ReservationStatus seatStatus,
        LocalDateTime reservedDate,
        LocalDateTime reservedUntilDate
) {
}
