package com.hhplus.concert_reservation.core.domain.user;

import com.hhplus.concert_reservation.core.domain.user.entities.Users;
import com.hhplus.concert_reservation.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Long getBalance(String token) {
        long userId = Users.extractUserIdFromJwt(token);
        System.out.println("userId check");
        System.out.println(userId);
        return userRepository.findById(userId).getBalance();
    }

    @Transactional(rollbackFor = Exception.class)
    public long chargeBalance(String token, long balance) {
        long userId = Users.extractUserIdFromJwt(token);
        Users users = userRepository.findById(userId);
        users.chargeBalance(balance);
        return users.getBalance();
    }

    @Transactional(rollbackFor = Exception.class)
    public void createUser() {
        Users newUser = new Users(100000, LocalDateTime.now(), false);
        userRepository.save(newUser);
    }
}
