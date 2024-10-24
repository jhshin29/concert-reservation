package com.hhplus.concert_reservation.core.domain.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.entities.Concert;

public interface ConcertRepository {
    Concert findById(Long concertId);
}
