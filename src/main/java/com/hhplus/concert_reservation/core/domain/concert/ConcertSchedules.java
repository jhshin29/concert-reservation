package com.hhplus.concert_reservation.core.domain.concert;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConcertSchedules(
        long scheduleId,
        String concertTitle,
        LocalDate openDate,
        LocalDateTime startAt,
        LocalDateTime endAt,
        boolean isSoldOut
) {
}
