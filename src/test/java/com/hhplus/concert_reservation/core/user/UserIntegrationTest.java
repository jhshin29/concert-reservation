package com.hhplus.concert_reservation.core.user;

import com.hhplus.concert_reservation.core.domain.user.UserService;
import com.hhplus.concert_reservation.core.domain.user.entities.Users;
import com.hhplus.concert_reservation.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void 잔액_조회() {

        Users users = userRepository.findById(1L);

        String token = "eyJhbGciOiJub25lIn0.eyJ1c2VySWQiOjEsInRva2VuIjoiZTBjYzY5ZTUtM2MyMS00YjAwLTgyOTQtMzA2MzcyOTg2MGZiIiwiZW50ZXJlZEF0IjoxNzI5Nzk3NjEyNzc2LCJleHBpcmVkQXQiOjE3Mjk3OTc5MTI3NzZ9.";


        userService.chargeBalance(token, 1000L);
        userService.chargeBalance(token, 1000L);
        Long balance = userService.getBalance(token);

        assertThat(balance).isEqualTo(102000L);
    }

    @Test
    void 잔액_충전_동시성_테스트() throws InterruptedException {

        long chargeAmount = 1000L;
        int threadCount = 4;

        Users users = userRepository.findById(1L);
        String token = "eyJhbGciOiJub25lIn0.eyJ1c2VySWQiOjEsInRva2VuIjoiZTBjYzY5ZTUtM2MyMS00YjAwLTgyOTQtMzA2MzcyOTg2MGZiIiwiZW50ZXJlZEF0IjoxNzI5Nzk3NjEyNzc2LCJleHBpcmVkQXQiOjE3Mjk3OTc5MTI3NzZ9.";

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    userService.chargeBalance(token, chargeAmount);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Users updatedUser = userRepository.findById(users.getId());
        assertThat(updatedUser.getBalance()).isEqualTo(users.getBalance() + chargeAmount * threadCount);
    }
}
