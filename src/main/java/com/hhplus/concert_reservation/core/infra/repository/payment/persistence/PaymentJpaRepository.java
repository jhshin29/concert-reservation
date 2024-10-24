package com.hhplus.concert_reservation.core.infra.repository.payment.persistence;

import com.hhplus.concert_reservation.core.domain.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByReservationId(Long id);
}
