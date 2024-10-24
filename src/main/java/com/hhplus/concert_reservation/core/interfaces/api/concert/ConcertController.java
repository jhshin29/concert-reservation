package com.hhplus.concert_reservation.core.interfaces.api.concert;

import com.hhplus.concert_reservation.core.domain.concert.ConcertSchedules;
import com.hhplus.concert_reservation.core.domain.concert.ConcertSeats;
import com.hhplus.concert_reservation.core.domain.concert.ConcertService;
import com.hhplus.concert_reservation.core.interfaces.api.common.Response;
import com.hhplus.concert_reservation.core.interfaces.api.concert.response.GetSchedulesResponse;
import com.hhplus.concert_reservation.core.interfaces.api.concert.response.GetSeatsResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concert")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/schedules")
    public Response<List<GetSchedulesResponse>> getConcertSchedules(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token
    ) {
        List<ConcertSchedules> concertSchedules = concertService.getConcertSchedules(token);
        return Response.ok(GetSchedulesResponse.of(concertSchedules));
    }

    @GetMapping("/schedules/{scheduleId}")
    public Response<?> getAvailableSeats(
            @Schema(description = "대기열 토큰") @RequestHeader("Authorization") String token,
            @Schema(description = "콘서트 스케줄 ID") @PathVariable("scheduleId") long scheduleId
    ) {
        List<ConcertSeats> availableSeats = concertService.getAvailableSeats(token, scheduleId);
        return Response.ok(GetSeatsResponse.of(availableSeats));
    }
}
