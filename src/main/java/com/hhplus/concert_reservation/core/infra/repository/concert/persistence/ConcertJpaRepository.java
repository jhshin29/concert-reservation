package com.hhplus.concert_reservation.core.infra.repository.concert.persistence;

import com.hhplus.concert_reservation.core.domain.concert.entities.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {
}
