package com.hhplus.concert_reservation.core.infra.repository.user.repository;

import com.hhplus.concert_reservation.core.domain.user.repository.UserRepository;
import com.hhplus.concert_reservation.core.infra.repository.user.persistence.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
}
