package com.hhplus.concert_reservation.core.interfaces.api.interceptor;

import com.hhplus.concert_reservation.core.domain.queue.entities.Queue;
import com.hhplus.concert_reservation.core.domain.queue.repository.QueueRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class CustomInterceptor implements HandlerInterceptor {

    private final QueueRepository queueRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            throw new IllegalArgumentException("토큰이 존재하지 않습니다.");
        }
        Queue queue = queueRepository.findByToken(token);
        queue.checkToken();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
