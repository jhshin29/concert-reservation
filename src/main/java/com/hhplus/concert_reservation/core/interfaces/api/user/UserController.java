package com.hhplus.concert_reservation.core.interfaces.api.user;

import com.hhplus.concert_reservation.core.domain.user.UserService;
import com.hhplus.concert_reservation.core.interfaces.api.common.Response;
import com.hhplus.concert_reservation.core.interfaces.api.user.request.ChargeBalanceRequest;
import com.hhplus.concert_reservation.core.interfaces.api.user.response.ChargeBalanceResponse;
import com.hhplus.concert_reservation.core.interfaces.api.user.response.UserBalanceResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public Response<Boolean> createUser() {
        userService.createUser();
        return Response.ok(true);
    }

    // 결제에 사용될 잔액 조회
    @GetMapping("/balance")
    public Response<UserBalanceResponse> getBalance(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token
    ) {
        return Response.ok(new UserBalanceResponse(userService.getBalance(token)));
    }

    // 결제에 사용될 잔액 충전
    @PostMapping("/balance")
    public Response<?> chargeBalance(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token,
            @RequestBody ChargeBalanceRequest request
    ) {
        return Response.ok(new ChargeBalanceResponse(userService.chargeBalance(token, request.balance())));
    }
}
