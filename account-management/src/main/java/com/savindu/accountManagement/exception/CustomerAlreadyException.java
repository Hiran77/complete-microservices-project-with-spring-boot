package com.savindu.accountManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyException extends RuntimeException {
    public CustomerAlreadyException(String message) {
        super(message);
    }
}
