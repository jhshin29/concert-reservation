package com.hhplus.concert_reservation.core.interfaces.api.concert.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record PaymentConcertRequest(
        @Schema(description = "콘서트 예약 id", defaultValue = "1")
        long reservationId
) {
}
