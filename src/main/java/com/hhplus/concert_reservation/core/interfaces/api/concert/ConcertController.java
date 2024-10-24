package com.hhplus.concert_reservation.core.interfaces.api.concert;

import com.hhplus.concert_reservation.core.domain.concert.*;
import com.hhplus.concert_reservation.core.interfaces.api.common.Response;
import com.hhplus.concert_reservation.core.interfaces.api.concert.request.PaymentConcertRequest;
import com.hhplus.concert_reservation.core.interfaces.api.concert.request.ReserveConcertRequest;
import com.hhplus.concert_reservation.core.interfaces.api.concert.response.GetSchedulesResponse;
import com.hhplus.concert_reservation.core.interfaces.api.concert.response.GetSeatsResponse;
import com.hhplus.concert_reservation.core.interfaces.api.concert.response.PaymentResponse;
import com.hhplus.concert_reservation.core.interfaces.api.concert.response.ReservationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "콘서트 API", description = "콘서트 예약 관련 API입니다. 모든 API는 대기열 토큰 헤더(Authorization)이 필요합니다.")
@RestController
@RequestMapping("/api/concert")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/schedules")
    @Operation(summary = "예약 가능 콘서트 일정 조회")
    public Response<List<GetSchedulesResponse>> getConcertSchedules(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token
    ) {
        List<ConcertSchedules> concertSchedules = concertService.getConcertSchedules(token);
        return Response.ok(GetSchedulesResponse.of(concertSchedules));
    }

    @GetMapping("/schedules/{scheduleId}")
    @Operation(summary = "콘서트 스케줄에 맞는 좌석 조회")
    public Response<List<GetSeatsResponse>> getAvailableSeats(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token,
            @Schema(description = "콘서트 스케줄 ID") @PathVariable("scheduleId") long scheduleId
    ) {
        List<ConcertSeats> availableSeats = concertService.getAvailableSeats(token, scheduleId);
        return Response.ok(GetSeatsResponse.of(availableSeats));
    }

    @PostMapping("/reservation")
    @Operation(summary = "콘서트 좌석 임시예약 (5분)")
    public Response<ReservationResponse> reserveConcert(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token,
            @RequestBody ReserveConcertRequest request
    ) {
        ReservationResult reservationResult = concertService.reserveConcert(token, request.scheduleId(), request.seatId());
        return Response.ok(ReservationResponse.of(reservationResult));
    }

    @PostMapping("/payment")
    @Operation(summary = "결제 완료 후 예약 컨펌")
    public Response<PaymentResponse> paymentConcert(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token,
            @RequestBody PaymentConcertRequest request
    ) {
        PaymentConcertResult paymentResult = concertService.paymentConcert(token, request.reservationId());
        return Response.ok(PaymentResponse.of(paymentResult));
    }
}
