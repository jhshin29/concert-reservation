package com.hhplus.concert_reservation.core.infra.repository.concert.persistence;

import com.hhplus.concert_reservation.core.domain.concert.ConcertSeats;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeat, Long> {

    @Query("""
                select new com.hhplus.concert_reservation.core.domain.concert.ConcertSeats(
                    cs.id,
                    cs.amount,
                    cs.seatNumber,
                    cs.seatStatus
                )
                from
                     ConcertSeat cs
                where
                    cs.concertScheduleId = :scheduleId
                    and cs.seatStatus = "AVAILABLE"
            """)
    List<ConcertSeats> findConcertSeats(@Param("scheduleId") long scheduleId);
}
