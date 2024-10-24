package com.hhplus.concert_reservation.core.infra.repository.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.entities.Concert;
import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertRepository;
import com.hhplus.concert_reservation.core.infra.repository.concert.persistence.ConcertJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;

    @Override
    public Concert findById(Long concertId) {
        return concertJpaRepository.findById(concertId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 가진 콘서트가 존재하지 않습니다."));
    }
}
