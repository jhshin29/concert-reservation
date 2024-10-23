package com.hhplus.concert_reservation.core.domain.queue.entities;

public record SelectQueueTokenResult(
        long queuePosition,
        QueueStatus status
) {
}
