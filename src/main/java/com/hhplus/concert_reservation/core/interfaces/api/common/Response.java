package com.hhplus.concert_reservation.core.interfaces.api.common;

import org.springframework.http.HttpStatus;

public record Response<T>(
        int status,
        String message,
        T data
) {
    public static <T> Response<T> ok(T data) {
        return new Response<>(HttpStatus.OK.value(), "success", data);
    }
}
