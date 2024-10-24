package com.hhplus.concert_reservation.core.interfaces.api.support;

import com.hhplus.concert_reservation.core.interfaces.api.common.Response;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Response<?>> handleNullPointerException(NullPointerException ex) {
        Response<?> response = new Response<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "서버에서 처리 중 오류가 발생했습니다.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Response<?> response = new Response<>(
                HttpStatus.BAD_REQUEST.value(),
                "잘못된 요청입니다.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response<?>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Response<?> response = new Response<>(
                HttpStatus.NOT_FOUND.value(),
                "요청한 항목을 찾을 수 없습니다.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response<?>> handleAccessDeniedException(AccessDeniedException ex) {
        Response<?> response = new Response<>(
                HttpStatus.FORBIDDEN.value(),
                "접근이 거부되었습니다.", // 사용자 친화적인 메시지
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handleException(Exception ex) {
        Response<?> response = new Response<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "알 수 없는 문제 발생. 관리자에게 문의해주세요",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
