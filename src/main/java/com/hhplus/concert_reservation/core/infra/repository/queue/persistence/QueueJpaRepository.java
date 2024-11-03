package com.hhplus.concert_reservation.core.infra.repository.queue.persistence;

import com.hhplus.concert_reservation.core.domain.queue.entities.Queue;
import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QueueJpaRepository extends JpaRepository<Queue, Long> {

    @Query("""
                select q
                from Queue q
                where q.userId = :userId
                    and (q.status = "WAITING" or q.status = "PROGRESS")
                order by q.enteredAt desc
                limit 1
            """)
    Optional<Queue> findByUserIdAndStatusWaitingOrProgress(@Param("userId") long userId);

    Optional<Queue> findByToken(String token);

    List<Queue> findAllByStatusOrderByIdDesc(QueueStatus queueStatus);

    @Query("""
                select count(1)
                from Queue q
                where q.status = :status
                    and q.enteredAt < :enteredAt
            """)
    Long findByStatusAndAlreadyEnteredBy(
            @Param("enteredAt") LocalDateTime enteredAt,
            @Param("status") QueueStatus queueStatus
    );

    @Modifying
    @Query("""
            update Queue q
            set q.status = :updateStatus
            where q.status = :currentStatus and q.expiredAt <= :currentTime
            """)
    void updateTokenToExpired(
            @Param("updateStatus") QueueStatus updateStatus,
            @Param("currentStatus") QueueStatus currentStatus,
            @Param("currentTime") LocalDateTime currentTime
    );
}
