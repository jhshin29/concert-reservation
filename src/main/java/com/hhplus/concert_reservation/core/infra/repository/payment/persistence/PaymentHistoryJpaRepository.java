package com.hhplus.concert_reservation.core.infra.repository.payment.persistence;

import com.hhplus.concert_reservation.core.domain.payment.entities.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentHistory, Long> {
}
