package com.hhplus.concert_reservation.core.infra.repository.queue.repository;

import com.hhplus.concert_reservation.core.domain.queue.repository.QueueRepository;
import com.hhplus.concert_reservation.core.infra.repository.queue.persistence.QueueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueueRepositoryImpl implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;
}
