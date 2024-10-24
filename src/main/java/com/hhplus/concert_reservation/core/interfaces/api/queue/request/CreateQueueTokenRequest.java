package com.hhplus.concert_reservation.core.interfaces.api.queue.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateQueueTokenRequest(
        @Schema(description = "사용자 id", defaultValue = "1")
        Long userId
) {
}
