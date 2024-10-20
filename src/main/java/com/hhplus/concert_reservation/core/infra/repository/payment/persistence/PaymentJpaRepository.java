package com.hhplus.concert_reservation.core.infra.repository.payment.persistence;

import com.hhplus.concert_reservation.core.domain.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
}
