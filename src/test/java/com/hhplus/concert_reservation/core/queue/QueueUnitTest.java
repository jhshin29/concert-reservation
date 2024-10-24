package com.hhplus.concert_reservation.core.queue;

import com.hhplus.concert_reservation.core.domain.queue.entities.Queue;
import com.hhplus.concert_reservation.core.domain.queue.entities.QueueStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueueUnitTest {

    @Test
    void 대기열이_30명_초과면_상태가_변경되지_않는다() {
        Queue queue = new Queue(1L, "test-token", QueueStatus.WAITING, LocalDateTime.now().plusMinutes(10));
        List<Queue> queueList = Collections.nCopies(31, queue);

        queue.checkWaitingQueue(queueList);

        assertEquals(QueueStatus.WAITING, queue.getStatus());
    }

    @Test
    void 대기열이_30명_미만일_경우_상태가_PROGRESS로_변경된다() {

        Queue queue = new Queue(1L, "test-token", QueueStatus.WAITING, LocalDateTime.now().plusMinutes(10));
        List<Queue> queueList = Collections.nCopies(30, queue);

        queue.checkWaitingQueue(queueList);

        assertEquals(QueueStatus.PROGRESS, queue.getStatus());
    }

    @Test
    void 토큰유효성검사_상태_WAITING_만료시간없음() {
        Queue queue = new Queue(1L, "token", QueueStatus.WAITING, null);

        boolean isValid = queue.isTokenValid();

        assertTrue(isValid);
    }

    @Test
    void 토큰유효성검사_상태가Waiting이고_만료시간이지났음() {
        Queue queue = new Queue(1L, "test-token" ,QueueStatus.WAITING, LocalDateTime.now().minusMinutes(10));

        boolean isValid = queue.isTokenValid();

        assertFalse(isValid); // 토큰이 유효하지 않음
    }
}
