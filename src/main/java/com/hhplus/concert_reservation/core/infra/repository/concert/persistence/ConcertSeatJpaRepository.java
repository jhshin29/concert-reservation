package com.hhplus.concert_reservation.core.infra.repository.concert.persistence;

import com.hhplus.concert_reservation.core.domain.concert.dto.ConcertSeats;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT cs FROM ConcertSeat cs WHERE cs.id = :seatId")
    Optional<ConcertSeat> findByIdWithLock(@Param("seatId") long seatId);
}
