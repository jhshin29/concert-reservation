package com.hhplus.concert_reservation.core.infra.repository.concert.persistence;

import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertScheduleJpaRepository extends JpaRepository<ConcertSchedule, Long> {
}
