package com.zxc.lyricannotations.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request, Model model) {
        logger.error("Global exception handler caught exception for URL: {}", request.getRequestURL(), ex);

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("path", request.getRequestURI());
        model.addAttribute("timestamp", LocalDateTime.now());
        model.addAttribute("exceptionType", ex.getClass().getSimpleName());

        return "error";
    }
}