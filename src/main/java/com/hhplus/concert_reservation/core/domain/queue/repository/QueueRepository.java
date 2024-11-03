package com.hhplus.concert_reservation.core.domain.queue.repository;

import com.hhplus.concert_reservation.core.domain.queue.entities.Queue;
import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface QueueRepository {

    void save(Queue queue);

    List<Queue> findAll();

    Queue findByUserIdAndStatusWaitingOrProgress(long userId);

    Queue findByToken(String token);

    List<Queue> findByStatusOrderByIdDesc(QueueStatus queueStatus);

    Long findByStatusAndAlreadyEnteredBy(LocalDateTime enteredAt, QueueStatus queueStatus);

    void updateTokenToExpired();
}
