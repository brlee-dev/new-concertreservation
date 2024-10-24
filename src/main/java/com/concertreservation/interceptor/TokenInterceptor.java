package com.concertreservation.interceptor;

import com.concertreservation.application.service.QueueTokenService;
import com.concertreservation.domain.model.QueueToken;
import com.concertreservation.exception.BusinessException;
import com.concertreservation.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final QueueTokenService queueTokenService;

    public TokenInterceptor(QueueTokenService queueTokenService) {
        this.queueTokenService = queueTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tokenId = request.getParameter("tokenId");
        if (tokenId == null || tokenId.isEmpty()) {
            throw new BusinessException(ErrorCode.TOKEN_NOT_FOUND);
        }

        // 토큰 유효성 검사
        QueueToken token = queueTokenService.getQueueStatus(tokenId);

        // 토큰을 요청 속성에 저장하여 이후에 사용할 수 있게 함
        request.setAttribute("token", token);

        return true;
    }
}

