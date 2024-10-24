package com.hhplus.concert_reservation.core.interfaces.api.concert.response;

import com.hhplus.concert_reservation.core.domain.concert.ReservationResult;
import com.hhplus.concert_reservation.core.domain.reservation.entities.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(
        long reservationId,
        ReservationStatus seatStatus,
        LocalDateTime reservedDate,
        LocalDateTime reservedUntilDate
) {
    public static ReservationResponse of(ReservationResult result) {
        return new ReservationResponse(
                result.reservationId(),
                result.seatStatus(),
                result.reservedDate(),
                result.reservedUntilDate()
        );
    }
}
