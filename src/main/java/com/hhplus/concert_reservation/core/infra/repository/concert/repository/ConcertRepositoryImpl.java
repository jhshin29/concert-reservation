package com.hhplus.concert_reservation.core.infra.repository.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertRepository;
import com.hhplus.concert_reservation.core.infra.repository.concert.persistence.ConcertJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;
}
