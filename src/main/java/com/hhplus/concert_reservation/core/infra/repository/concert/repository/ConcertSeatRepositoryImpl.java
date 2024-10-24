package com.hhplus.concert_reservation.core.infra.repository.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.ConcertSeats;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSeat;
import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertSeatRepository;
import com.hhplus.concert_reservation.core.infra.repository.concert.persistence.ConcertSeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertSeatRepositoryImpl implements ConcertSeatRepository {

    private final ConcertSeatJpaRepository concertSeatJpaRepository;

    @Override
    public List<ConcertSeats> findConcertSeats(long scheduleId) {
        return concertSeatJpaRepository.findConcertSeats(scheduleId);
    }

    @Override
    public ConcertSeat findByIdWithLock(long seatId) {
        return concertSeatJpaRepository.findByIdWithLock(seatId)
                .orElseThrow(() -> new IllegalArgumentException("해당 좌석을 조회할 수 없습니다."));
    }
}
