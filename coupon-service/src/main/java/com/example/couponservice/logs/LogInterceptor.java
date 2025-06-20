package com.example.couponservice.logs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogInterceptor implements HandlerInterceptor {
    long startTime;
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         startTime = System.currentTimeMillis();
        LOGGER.info("Request URL: {} {} - Timestamp: {}", request.getMethod(), request.getRequestURI(), getCurrentTimestamp());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long duration = System.currentTimeMillis() - startTime;  // Calculate how long the request took.

        LOGGER.info("Response Status: {} - Timestamp: {} - Duration: {}ms", response.getStatus(), getCurrentTimestamp(),duration);
    }

    private String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return now.format(formatter);
    }
}
