package com.hhplus.concert_reservation.core.infra.repository.concert.persistence;

import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeat, Long> {
}
