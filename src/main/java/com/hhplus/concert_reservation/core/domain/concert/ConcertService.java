package com.hhplus.concert_reservation.core.domain.concert;

import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertScheduleRepository;
import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertSeatRepository;
import com.hhplus.concert_reservation.core.domain.user.entities.Users;
import com.hhplus.concert_reservation.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final UserRepository userRepository;
    private final ConcertScheduleRepository concertScheduleRepository;
    private final ConcertSeatRepository concertSeatRepository;

    @Transactional(readOnly = true)
    public List<ConcertSchedules> getConcertSchedules(String token) {
        long userId = Users.extractUserIdFromJwt(token);
        userRepository.findById(userId);
        return concertScheduleRepository.findConcertSchedules();
    }

    @Transactional(readOnly = true)
    public List<ConcertSeats> getAvailableSeats(String token, long scheduleId) {
        long userId = Users.extractUserIdFromJwt(token);
        userRepository.findById(userId);
        return concertSeatRepository.findConcertSeats(scheduleId);
    }
}
