package com.hhplus.concert_reservation.core.infra.repository.user.persistence;

import com.hhplus.concert_reservation.core.domain.user.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
}
