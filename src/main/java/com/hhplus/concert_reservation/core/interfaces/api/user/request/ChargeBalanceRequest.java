package com.hhplus.concert_reservation.core.interfaces.api.user.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ChargeBalanceRequest(
        @Schema(description = "충전 금액", defaultValue = "50000")
        long balance
) {
}
