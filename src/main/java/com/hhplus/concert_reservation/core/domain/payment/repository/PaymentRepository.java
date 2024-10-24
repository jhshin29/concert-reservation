package com.hhplus.concert_reservation.core.domain.payment.repository;

import com.hhplus.concert_reservation.core.domain.payment.entities.Payment;

public interface PaymentRepository {
    void save(Payment payment);

    Payment findByReservationId(Long id);
}
