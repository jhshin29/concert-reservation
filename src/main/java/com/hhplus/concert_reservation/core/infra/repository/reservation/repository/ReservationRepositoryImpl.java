package com.hhplus.concert_reservation.core.infra.repository.reservation.repository;

import com.hhplus.concert_reservation.core.domain.reservation.entities.Reservation;
import com.hhplus.concert_reservation.core.domain.reservation.repository.ReservationRepository;
import com.hhplus.concert_reservation.core.infra.repository.reservation.persistence.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public void save(Reservation reservation) {
        reservationJpaRepository.save(reservation);
    }

    @Override
    public Reservation findById(long reservationId) {
        return reservationJpaRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약 정보가 존재하지 않습니다."));
    }

}
