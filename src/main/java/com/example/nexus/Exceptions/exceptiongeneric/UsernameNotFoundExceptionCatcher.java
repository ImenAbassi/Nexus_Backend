package com.example.nexus.Exceptions.exceptiongeneric;

import com.example.nexus.Exceptions.handler.ErrorCodes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsernameNotFoundExceptionCatcher extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ErrorCodes errorCodes;

    public UsernameNotFoundExceptionCatcher(String message, Throwable cause) {
        super(message, cause);
        log.error(message);
    }

    public UsernameNotFoundExceptionCatcher(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
        log.error(message);
    }

    public UsernameNotFoundExceptionCatcher(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
        log.error(message);
    }

    public UsernameNotFoundExceptionCatcher(ErrorCodes errorCodes) {
        super(errorCodes.toString());
        this.errorCodes = errorCodes;
        log.error(errorCodes.toString());

    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }

}
