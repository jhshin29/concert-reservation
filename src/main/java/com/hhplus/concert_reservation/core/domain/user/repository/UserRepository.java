package com.hhplus.concert_reservation.core.domain.user.repository;

import com.hhplus.concert_reservation.core.domain.user.entities.Users;

public interface UserRepository {
    Users findById(long userId);

    void save(Users users);
}
