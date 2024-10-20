package com.hhplus.concert_reservation.core.infra.repository.reservation.repository;

import com.hhplus.concert_reservation.core.domain.reservation.repository.ReservationRepository;
import com.hhplus.concert_reservation.core.infra.repository.reservation.persistence.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;
}
