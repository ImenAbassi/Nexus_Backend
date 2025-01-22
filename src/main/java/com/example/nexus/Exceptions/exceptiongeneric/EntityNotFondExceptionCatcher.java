package com.example.nexus.Exceptions.exceptiongeneric;

import com.example.nexus.Exceptions.handler.ErrorCodes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityNotFondExceptionCatcher extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private com.example.nexus.Exceptions.handler.ErrorCodes errorCodes;

    public EntityNotFondExceptionCatcher() {
    }

    public EntityNotFondExceptionCatcher(String message, Throwable cause) {
        super(message, cause);
        log.error(message);
    }

    public EntityNotFondExceptionCatcher(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
        log.error(message);
    }

    public EntityNotFondExceptionCatcher(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
        log.error(message);

    }

    public EntityNotFondExceptionCatcher(ErrorCodes errorCodes) {
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
