package com.hhplus.concert_reservation.core.user;

import com.hhplus.concert_reservation.core.domain.user.entities.Users;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserUnitTest {

    @Test
    void 유저_잔액_충전_검증() {

        Users user = new Users(1L, 100000L);

        user.chargeBalance(1000L);

        assertThat(user.getBalance()).isEqualTo(101000L);
    }

    @Test
    void 결제_잔액_부족_예외_발생() {

        Users user = new Users(1L, 10000L);

        assertThrows(IllegalArgumentException.class, () -> {
            user.checkConcertAmount(10001L);
        }, "결제할 잔액이 부족합니다.");
    }
}
