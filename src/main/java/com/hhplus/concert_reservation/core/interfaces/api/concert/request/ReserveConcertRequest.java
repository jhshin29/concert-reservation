package com.hhplus.concert_reservation.core.interfaces.api.concert.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ReserveConcertRequest(
        @Schema(description = "콘서트 스케줄 id", defaultValue = "1")
        long scheduleId,
        @Schema(description = "콘서트 좌석 id", defaultValue = "1")
        long seatId
) {
}
