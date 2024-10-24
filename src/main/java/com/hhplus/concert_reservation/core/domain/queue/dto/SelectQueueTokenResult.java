package com.hhplus.concert_reservation.core.domain.queue.dto;

import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;

public record SelectQueueTokenResult(
        long queuePosition,
        QueueStatus status
) {
}
