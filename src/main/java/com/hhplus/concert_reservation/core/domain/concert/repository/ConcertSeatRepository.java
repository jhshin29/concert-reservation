package com.hhplus.concert_reservation.core.domain.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.dto.ConcertSeats;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSeat;

import java.util.List;

public interface ConcertSeatRepository {
    List<ConcertSeats> findConcertSeats(long scheduleId);

    ConcertSeat findByIdWithLock(long seatId);
}
