package com.hhplus.concert_reservation.core.concert;

import com.hhplus.concert_reservation.core.domain.concert.entities.Concert;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSchedule;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSeat;
import com.hhplus.concert_reservation.core.domain.concert.entities.SeatStatus;
import com.hhplus.concert_reservation.core.domain.payment.entities.Payment;
import com.hhplus.concert_reservation.core.domain.payment.entities.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConcertServiceUnitTest {

    @Test
    void 좌석_AVAILABLE_상태일_때_예약_가능() {
        ConcertSeat concertSeat = new ConcertSeat(1L, 1L, 500L, 10, SeatStatus.AVAILABLE, null);

        assertDoesNotThrow(concertSeat::isReserveCheck);
    }

    @Test
    void 좌석_RESERVED_상태일_때_예약_불가능() {
        ConcertSeat concertSeat = new ConcertSeat(1L, 1L, 500L, 10, SeatStatus.RESERVED, null);

        assertThrows(IllegalArgumentException.class, concertSeat::isReserveCheck, "해당 좌석은 예약할 수 없는 상태 입니다.");
    }

    @Test
    void 좌석_TEMP_RESERVED_상태일_때_예약_불가능() {
        ConcertSeat concertSeat = new ConcertSeat(1L, 1L, 500L, 10, SeatStatus.TEMP_RESERVED, null);

        assertThrows(IllegalArgumentException.class, concertSeat::isReserveCheck, "해당 좌석은 예약할 수 없는 상태 입니다.");
    }

    @Test
    void 결제를_진행한다() {
        long userId = 1L;

        Concert concert = new Concert(1L, "testConcert");
        ConcertSchedule concertSchedule = new ConcertSchedule(1L, concert.getId(), LocalDate.now(), LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5), 50, 50, false);
        ConcertSeat concertSeat = new ConcertSeat(1L, concertSchedule.getId(), 500L, 10, SeatStatus.RESERVED, null);

        Payment payment = Payment.enterPayment(userId, 1L, concertSeat.getAmount(), PaymentStatus.PROGRESS);

        // 결제 완료 처리
        payment.finishPayment();

        assertEquals(PaymentStatus.DONE, payment.getStatus());
        assertEquals(500L, payment.getPrice()); // 결제 후 금액 확인
    }

    @Test
    void 콘서트가_매진되어_예약할_수_없다() {
        ConcertSchedule concertSchedule = new ConcertSchedule(1L, 1L, LocalDate.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3), 100, 0, true);

        assertThrows(IllegalArgumentException.class, concertSchedule::isSoldOutCheck);
    }
}
