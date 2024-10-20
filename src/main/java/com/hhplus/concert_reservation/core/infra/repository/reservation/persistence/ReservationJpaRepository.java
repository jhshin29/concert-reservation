package com.hhplus.concert_reservation.core.infra.repository.reservation.persistence;

import com.hhplus.concert_reservation.core.domain.reservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
}
