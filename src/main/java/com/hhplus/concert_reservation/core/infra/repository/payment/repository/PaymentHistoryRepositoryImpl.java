package com.hhplus.concert_reservation.core.infra.repository.payment.repository;

import com.hhplus.concert_reservation.core.domain.payment.repository.PaymentHistoryRepository;
import com.hhplus.concert_reservation.core.infra.repository.payment.persistence.PaymentHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentHistoryRepositoryImpl implements PaymentHistoryRepository {

    private final PaymentHistoryJpaRepository paymentHistoryJpaRepository;
}
