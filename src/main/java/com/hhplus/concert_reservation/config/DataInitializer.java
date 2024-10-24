package com.hhplus.concert_reservation.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(JdbcTemplate jdbcTemplate) {
        return args -> {
            jdbcTemplate.execute("insert into users (balance, created_at) values (100000, CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into users (balance, created_at) values (500000, CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into users (balance, created_at) values (300000, CURRENT_TIMESTAMP)");

            jdbcTemplate.execute("insert into concert (title, created_at) values ('허재 코치의 토크 콘서트', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert (title, created_at) values ('로이 코치의 토크 콘서트', CURRENT_TIMESTAMP)");

            jdbcTemplate.execute("insert into concert_schedule (concert_id, open_date, start_at, end_at, total_seats, remaining_seats, is_sold_out, created_at) values (1, '2024-10-27', '2024-10-27 12:00:00', '2024-10-27 13:00:00', 30, 30, false, CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_schedule (concert_id, open_date, start_at, end_at, total_seats, remaining_seats, is_sold_out, created_at) values (1, '2024-10-27', '2024-10-27 15:00:00', '2024-10-27 16:00:00', 30, 30, false, CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_schedule (concert_id, open_date, start_at, end_at, total_seats, remaining_seats, is_sold_out, created_at) values (2, '2024-10-28', '2024-10-28 12:00:00', '2024-10-28 13:00:00', 30, 30, false, CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_schedule (concert_id, open_date, start_at, end_at, total_seats, remaining_seats, is_sold_out, created_at) values (2, '2024-10-28', '2024-10-28 15:00:00', '2024-10-28 16:00:00', 30, 30, false, CURRENT_TIMESTAMP)");

            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 1, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 2, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 3, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 4, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 5, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 6, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 7, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 8, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 9, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (1, 10000, 10, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 1, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 2, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 3, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 4, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 5, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 6, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 7, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 8, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 9, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (2, 10000, 10, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 1, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 2, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 3, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 4, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 5, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 6, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 7, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 8, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 9, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (3, 10000, 10, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 1, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 2, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 3, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 4, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 5, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 6, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 7, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 8, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 9, 'AVAILABLE', CURRENT_TIMESTAMP)");
            jdbcTemplate.execute("insert into concert_seat (concert_schedule_id, amount, seat_number, seat_status, created_at) values (4, 10000, 10, 'AVAILABLE', CURRENT_TIMESTAMP)");
        };
    }
}
