package com.hhplus.concert_reservation.core.infra.repository.user.repository;

import com.hhplus.concert_reservation.core.domain.user.entities.Users;
import com.hhplus.concert_reservation.core.domain.user.repository.UserRepository;
import com.hhplus.concert_reservation.core.infra.repository.user.persistence.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Users findById(long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다."));
    }

    @Override
    public Users findByIdWithLock(long userId) {
        return userJpaRepository.findByIdWithLock(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다."));
    }
}
