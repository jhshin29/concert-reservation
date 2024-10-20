package com.hhplus.concert_reservation.core.infra.repository.payment.repository;

import com.hhplus.concert_reservation.core.domain.payment.repository.PaymentRepository;
import com.hhplus.concert_reservation.core.infra.repository.payment.persistence.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
}
