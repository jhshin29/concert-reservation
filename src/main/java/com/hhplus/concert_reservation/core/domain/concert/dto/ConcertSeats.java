package com.hhplus.concert_reservation.core.domain.concert.dto;

import com.hhplus.concert_reservation.core.domain.concert.entities.SeatStatus;

public record ConcertSeats(
        long seatId,
        long amount,
        int seatNumber,
        SeatStatus seatstatus
) {
}
