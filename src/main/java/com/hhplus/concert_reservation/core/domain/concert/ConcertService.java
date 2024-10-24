package com.hhplus.concert_reservation.core.domain.concert;

import com.hhplus.concert_reservation.core.domain.concert.dto.ConcertSchedules;
import com.hhplus.concert_reservation.core.domain.concert.dto.ConcertSeats;
import com.hhplus.concert_reservation.core.domain.concert.dto.PaymentConcertResult;
import com.hhplus.concert_reservation.core.domain.concert.dto.ReservationResult;
import com.hhplus.concert_reservation.core.domain.concert.entities.Concert;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSchedule;
import com.hhplus.concert_reservation.core.domain.concert.entities.ConcertSeat;
import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertRepository;
import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertScheduleRepository;
import com.hhplus.concert_reservation.core.domain.concert.repository.ConcertSeatRepository;
import com.hhplus.concert_reservation.core.domain.payment.entities.Payment;
import com.hhplus.concert_reservation.core.domain.payment.entities.PaymentHistory;
import com.hhplus.concert_reservation.core.domain.payment.entities.PaymentStatus;
import com.hhplus.concert_reservation.core.domain.payment.entities.PaymentType;
import com.hhplus.concert_reservation.core.domain.payment.repository.PaymentHistoryRepository;
import com.hhplus.concert_reservation.core.domain.payment.repository.PaymentRepository;
import com.hhplus.concert_reservation.core.domain.queue.entities.Queue;
import com.hhplus.concert_reservation.core.domain.queue.repository.QueueRepository;
import com.hhplus.concert_reservation.core.domain.reservation.entities.Reservation;
import com.hhplus.concert_reservation.core.domain.reservation.repository.ReservationRepository;
import com.hhplus.concert_reservation.core.domain.user.entities.Users;
import com.hhplus.concert_reservation.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final UserRepository userRepository;
    private final ConcertRepository concertRepository;
    private final ConcertScheduleRepository concertScheduleRepository;
    private final ConcertSeatRepository concertSeatRepository;
    private final QueueRepository queueRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    @Transactional(readOnly = true)
    public List<ConcertSchedules> getConcertSchedules(String token) {
        long userId = Users.extractUserIdFromJwt(token);
        userRepository.findById(userId);
        return concertScheduleRepository.findConcertSchedules();
    }

    @Transactional(readOnly = true)
    public List<ConcertSeats> getAvailableSeats(String token, long scheduleId) {
        long userId = Users.extractUserIdFromJwt(token);
        userRepository.findById(userId);
        return concertSeatRepository.findConcertSeats(scheduleId);
    }

    @Transactional(rollbackFor = Exception.class)
    public ReservationResult reserveConcert(String token, long scheduleId, long seatId) {
        long userId = Users.extractUserIdFromJwt(token);
        Users user = userRepository.findById(userId);

        Queue queue = queueRepository.findByToken(token);
        queue.tokenReserveCheck();

        ConcertSchedule concertSchedule = concertScheduleRepository.findById(scheduleId);
        concertSchedule.isSoldOutCheck();

        ConcertSeat concertSeat = concertSeatRepository.findByIdWithLock(seatId);
        concertSeat.isReserveCheck();

        Concert concert = concertRepository.findById(concertSchedule.getConcertId());
        Reservation reservation = Reservation.enterReservation(user, concert, concertSeat, concertSchedule);
        reservationRepository.save(reservation);

        Payment payment = Payment.enterPayment(userId, reservation.getId(), concertSeat.getAmount(), PaymentStatus.PROGRESS);
        paymentRepository.save(payment);

        return new ReservationResult(reservation.getId(), reservation.getStatus(), reservation.getReservedAt(), reservation.getReservedUntilAt());
    }

    @Transactional(rollbackFor = Exception.class)
    public PaymentConcertResult paymentConcert(String token, long reservationId) {
        long userId = Users.extractUserIdFromJwt(token);
        Users user = userRepository.findById(userId);

        Queue queue = queueRepository.findByToken(token);
        queue.tokenReserveCheck();

        Reservation reservation = reservationRepository.findById(reservationId);
        user.checkConcertAmount(reservation.getSeatAmount());

        ConcertSeat concertSeat = concertSeatRepository.findByIdWithLock(reservation.getSeatId());
        concertSeat.finishSeatReserve();
        queue.finishQueue();

        reservation.finishReserve();

        Payment payment = paymentRepository.findByReservationId(reservation.getId());
        payment.finishPayment();

        PaymentHistory paymentHistory = PaymentHistory.enterPaymentHistory(userId, payment.getPrice(), PaymentType.PAYMENT, payment.getId());
        paymentHistoryRepository.save(paymentHistory);

        return new PaymentConcertResult(concertSeat.getAmount(), reservation.getStatus(), queue.getStatus());
    }
}
