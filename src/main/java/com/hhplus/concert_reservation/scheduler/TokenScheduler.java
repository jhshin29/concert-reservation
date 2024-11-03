package com.hhplus.concert_reservation.scheduler;

import com.hhplus.concert_reservation.core.domain.queue.QueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenScheduler {

    private final QueueService queueService;

    @Scheduled(cron = "0 */1 * * * ?")
    public void dropToken() {
        log.info("Executing dropToken method to change token status");
        queueService.changeTokenToExpired();
        log.info("dropToken job is done");
    }
}
