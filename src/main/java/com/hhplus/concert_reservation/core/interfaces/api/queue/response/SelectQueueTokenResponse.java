package com.hhplus.concert_reservation.core.interfaces.api.queue.response;

import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;

public record SelectQueueTokenResponse(
        long queuePosition,
        QueueStatus status
) {
    public static SelectQueueTokenResponse of(long queuePosition, QueueStatus status) {
        return new SelectQueueTokenResponse(queuePosition, status);
    }
}
