package com.hhplus.concert_reservation.core.domain.payment.repository;

import com.hhplus.concert_reservation.core.domain.payment.entities.PaymentHistory;

public interface PaymentHistoryRepository {
    void save(PaymentHistory paymentHistory);
}
