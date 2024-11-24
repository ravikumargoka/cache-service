package com.ravi.cache.statistics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private String message;

    public RecordNotFoundException(String message, Throwable t) {
        super(message, t);
        this.message = message;
    }

    public RecordNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
