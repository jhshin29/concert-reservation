package com.hhplus.concert_reservation.core.infra.repository.queue.repository;

import com.hhplus.concert_reservation.core.domain.queue.entities.Queue;
import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;
import com.hhplus.concert_reservation.core.domain.queue.repository.QueueRepository;
import com.hhplus.concert_reservation.core.infra.repository.queue.persistence.QueueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueueRepositoryImpl implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;


    @Override
    public void save(Queue queue) {
        queueJpaRepository.save(queue);
    }

    @Override
    public List<Queue> findAll() {
        return queueJpaRepository.findAll();
    }

    @Override
    public Queue findByUserIdAndStatusWaitingOrProgress(long userId) {
        return queueJpaRepository.findByUserIdAndStatusWaitingOrProgress(userId)
                .orElse(null);
    }

    @Override
    public Queue findByToken(String token) {
        return queueJpaRepository.findByToken(token)
                .orElseThrow(() -> new NullPointerException("토큰에 해당하는 큐가 없습니다."));
    }

    @Override
    public List<Queue> findByStatusOrderByIdDesc(QueueStatus queueStatus) {
        return queueJpaRepository.findAllByStatusOrderByIdDesc(queueStatus);
    }

    @Override
    public Long findByStatusAndAlreadyEnteredBy(LocalDateTime enteredAt, QueueStatus queueStatus) {
        return queueJpaRepository.findByStatusAndAlreadyEnteredBy(enteredAt, queueStatus);
    }

}
