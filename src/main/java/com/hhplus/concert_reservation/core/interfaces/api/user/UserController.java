package com.hhplus.concert_reservation.core.interfaces.api.user;

import com.hhplus.concert_reservation.core.domain.user.UserService;
import com.hhplus.concert_reservation.core.interfaces.api.common.Response;
import com.hhplus.concert_reservation.core.interfaces.api.user.request.ChargeBalanceRequest;
import com.hhplus.concert_reservation.core.interfaces.api.user.response.ChargeBalanceResponse;
import com.hhplus.concert_reservation.core.interfaces.api.user.response.UserBalanceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 API", description = "사용자와 관련된 API입니다. 모든 API는 대기열 토큰 헤더(Authorization)이 필요합니다.")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/balance")
    @Operation(summary = "유저 잔액 조회 API")
    public Response<UserBalanceResponse> getBalance(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token
    ) {
        return Response.ok(new UserBalanceResponse(userService.getBalance(token)));
    }

    @PostMapping("/balance")
    @Operation(summary = "유저 잔액 충전 API")
    public Response<ChargeBalanceResponse> chargeBalance(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token,
            @RequestBody ChargeBalanceRequest request
    ) {
        return Response.ok(new ChargeBalanceResponse(userService.chargeBalance(token, request.balance())));
    }
}
