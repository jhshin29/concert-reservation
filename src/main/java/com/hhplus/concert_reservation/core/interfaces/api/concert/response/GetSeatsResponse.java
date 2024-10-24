package com.hhplus.concert_reservation.core.interfaces.api.concert.response;

import com.hhplus.concert_reservation.core.domain.concert.dto.ConcertSeats;
import com.hhplus.concert_reservation.core.domain.concert.entities.SeatStatus;

import java.util.List;

public record GetSeatsResponse(
        long seatId,
        long amount,
        int seatNumber,
        SeatStatus seatstatus
) {
    public static List<GetSeatsResponse> of(List<ConcertSeats> resultList) {
        return resultList.stream()
                .map(seats -> new GetSeatsResponse(
                        seats.seatId(),
                        seats.amount(),
                        seats.seatNumber(),
                        seats.seatstatus()
                )).toList();
    }
}
