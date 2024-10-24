package com.hhplus.concert_reservation.core.domain.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.ConcertSchedules;

import java.util.List;

public interface ConcertScheduleRepository {
    List<ConcertSchedules> findConcertSchedules();
}
