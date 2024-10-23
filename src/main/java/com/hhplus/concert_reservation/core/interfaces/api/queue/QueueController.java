package com.hhplus.concert_reservation.core.interfaces.api.queue;

import com.hhplus.concert_reservation.core.domain.queue.QueueService;
import com.hhplus.concert_reservation.core.domain.queue.entities.SelectQueueTokenResult;
import com.hhplus.concert_reservation.core.interfaces.api.common.Response;
import com.hhplus.concert_reservation.core.interfaces.api.queue.request.CreateQueueTokenRequest;
import com.hhplus.concert_reservation.core.interfaces.api.queue.response.CreateQueueTokenResponse;
import com.hhplus.concert_reservation.core.interfaces.api.queue.response.SelectQueueTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "대기열 API", description = "콘서트 대기열 토큰 발급/체크 API")
@RestController
@RequestMapping("/api/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;

    @PostMapping("/token")
    @Operation(summary = "대기열 토큰 발급 API")
    public Response<CreateQueueTokenResponse> createQueueToken(
            @RequestBody CreateQueueTokenRequest request
    ) {
        return Response.ok(new CreateQueueTokenResponse(queueService.createQueueToken(request.userId())));
    }

    @PostMapping("/token/check")
    @Operation(summary = "대기열 토큰 체크 API")
    public Response<SelectQueueTokenResponse> getQueueToken(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token
    ) {
        SelectQueueTokenResult response = queueService.checkQueue(token);
        return Response.ok(SelectQueueTokenResponse.of(response.queuePosition(), response.status()));
    }

}
