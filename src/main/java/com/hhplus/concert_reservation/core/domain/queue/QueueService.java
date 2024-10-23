package com.hhplus.concert_reservation.core.domain.queue;

import com.hhplus.concert_reservation.core.domain.queue.entities.Queue;
import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;
import com.hhplus.concert_reservation.core.domain.queue.entities.SelectQueueTokenResult;
import com.hhplus.concert_reservation.core.domain.queue.repository.QueueRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;

    @Transactional(rollbackFor = Exception.class)
    public String createQueueToken(Long userId) {
        Queue existedQueue = queueRepository.findByUserIdAndStatusWaitingOrProgress(userId);

        Queue queue = Queue.enterQueue(existedQueue, userId);

        queueRepository.save(queue);

        return queue.getToken();
    }

    @Transactional(readOnly = true)
    public SelectQueueTokenResult checkQueue(String token) {
        long queuePosition = 0L;
        Queue queue = queueRepository.findByToken(token);
        List<Queue> waitingQueueList = queueRepository.findByStatusOrderByIdDesc(QueueStatus.WAITING);

        queue.checkWaitingQueue(waitingQueueList);

        if (queue.getStatus().equals(QueueStatus.WAITING)) {
            queuePosition = queueRepository.findByStatusAndAlreadyEnteredBy(queue.getEnteredAt(), QueueStatus.WAITING) + 1;
        }

        return new SelectQueueTokenResult(queuePosition, queue.getStatus());
    }
}
