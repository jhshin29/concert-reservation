package com.hhplus.concert_reservation.core.domain.reservation.repository;

import com.hhplus.concert_reservation.core.domain.reservation.entities.Reservation;

public interface ReservationRepository {
    void save(Reservation reservation);

    Reservation findById(long reservationId);
}
