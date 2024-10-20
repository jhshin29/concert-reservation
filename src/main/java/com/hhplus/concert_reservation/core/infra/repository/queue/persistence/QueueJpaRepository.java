package com.hhplus.concert_reservation.core.infra.repository.queue.persistence;

import com.hhplus.concert_reservation.core.domain.queue.entities.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueJpaRepository extends JpaRepository<Queue, Long> {
}
