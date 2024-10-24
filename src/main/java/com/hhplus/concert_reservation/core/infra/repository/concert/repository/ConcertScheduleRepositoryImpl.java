package com.hhplus.concert_reservation.core.infra.repository.concert.repository;

import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSchedule;
import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertScheduleRepository;
import com.hhplus.concert_reservation.core.infra.repository.concert.persistence.ConcertScheduleJpaRepository;
import com.hhplus.concert_reservation.core.domain.concert.dto.ConcertSchedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertScheduleRepositoryImpl implements ConcertScheduleRepository {

    private final ConcertScheduleJpaRepository concertScheduleJpaRepository;

    @Override
    public List<ConcertSchedules> findConcertSchedules() {
        return concertScheduleJpaRepository.findConcertSchedules();
    }

    @Override
    public ConcertSchedule findById(long scheduleId) {
        return concertScheduleJpaRepository.findById(scheduleId)
                .orElseThrow(() -> new NullPointerException("해당 콘서트 스케줄이 존재하지 않습니다."));
    }
}
