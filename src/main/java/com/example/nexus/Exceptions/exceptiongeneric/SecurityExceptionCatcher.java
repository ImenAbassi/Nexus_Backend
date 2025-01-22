package com.example.nexus.Exceptions.exceptiongeneric;

import com.example.nexus.Exceptions.handler.ErrorCodes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityExceptionCatcher extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ErrorCodes errorCodes;

    public SecurityExceptionCatcher(String message, Throwable cause) {
        super(message, cause);
        log.error(message);
    }

    public SecurityExceptionCatcher(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
        log.error(message);
    }

    public SecurityExceptionCatcher(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
        log.error(message);
    }

    public SecurityExceptionCatcher(ErrorCodes errorCodes) {
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
