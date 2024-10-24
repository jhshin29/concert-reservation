package com.hhplus.concert_reservation.core.infra.repository.payment.repository;

import com.hhplus.concert_reservation.core.domain.payment.entities.Payment;
import com.hhplus.concert_reservation.core.domain.payment.repository.PaymentRepository;
import com.hhplus.concert_reservation.core.infra.repository.payment.persistence.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public void save(Payment payment) {
        paymentJpaRepository.save(payment);
    }

    @Override
    public Payment findByReservationId(Long id) {
        return paymentJpaRepository.findByReservationId(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약의 결제 정보가 존재하지 않습니다."));
    }
}
