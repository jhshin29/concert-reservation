package com.hhplus.concert_reservation.core.infra.repository.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertScheduleRepository;
import com.hhplus.concert_reservation.core.infra.repository.concert.persistence.ConcertScheduleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertScheduleRepositoryImpl implements ConcertScheduleRepository {

    private final ConcertScheduleJpaRepository concertScheduleJpaRepository;
}
