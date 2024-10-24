package com.hhplus.concert_reservation.core.domain.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.ConcertSeats;

import java.util.List;

public interface ConcertSeatRepository {
    List<ConcertSeats> findConcertSeats(long scheduleId);
}
