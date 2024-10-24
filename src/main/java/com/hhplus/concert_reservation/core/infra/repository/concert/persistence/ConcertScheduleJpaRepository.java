package com.hhplus.concert_reservation.core.infra.repository.concert.persistence;

import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSchedule;
import com.hhplus.concert_reservation.core.domain.concert.dto.ConcertSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConcertScheduleJpaRepository extends JpaRepository<ConcertSchedule, Long> {

    @Query("""
                select new com.hhplus.concert_reservation.core.domain.concert.dto.ConcertSchedules(
                    cs.id,
                    c.title,
                    cs.openDate,
                    cs.startAt,
                    cs.endAt,
                    cs.isSoldOut
                )
                from
                    ConcertSchedule cs join Concert c on cs.concertId = c.id
                where
                    cs.startAt >= current_timestamp
                    and cs.isSoldOut = false
                    and cs.isDelete = false
                    and c.isDelete = false
            
            """)
    List<ConcertSchedules> findConcertSchedules();
}
