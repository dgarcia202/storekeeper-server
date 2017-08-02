package com.dgarcia202.storekeeper.api.controller;

import com.dgarcia202.storekeeper.exception.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler
{
    public static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public void handleNotFound()
    {
        this.logger.warn("Resource not found");
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleControllerException(Throwable e)
    {
        this.logger.error("unexpected error", e);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
