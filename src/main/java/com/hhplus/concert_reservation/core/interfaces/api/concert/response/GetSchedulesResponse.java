package com.hhplus.concert_reservation.core.interfaces.api.concert.response;

import com.hhplus.concert_reservation.core.domain.concert.ConcertSchedules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record GetSchedulesResponse(
        long scheduleId,
        String concertTitle,
        LocalDate openDate,
        LocalDateTime startAt,
        LocalDateTime endAt,
        boolean isSoldOut
) {

    public static List<GetSchedulesResponse> of(List<ConcertSchedules> resultList) {
        return resultList.stream()
                .map(schedules -> new GetSchedulesResponse(
                        schedules.scheduleId(),
                        schedules.concertTitle(),
                        schedules.openDate(),
                        schedules.startAt(),
                        schedules.endAt(),
                        schedules.isSoldOut()
                )).toList();
    }
}
