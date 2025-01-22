/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.nexus.Exceptions.exceptiongeneric;

import com.example.nexus.Exceptions.handler.ErrorCodes;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author L60021414
 */
@Slf4j
public class DuplicationEntryExceptionCatcher  extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = 2L;
    private ErrorCodes errorCodes;

    public DuplicationEntryExceptionCatcher(String message, Throwable cause) {
        super(message, cause);
        log.error(message);
    }

    public DuplicationEntryExceptionCatcher(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
        log.error(message);
    }

    public DuplicationEntryExceptionCatcher(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
        log.error(message);
    }

    public DuplicationEntryExceptionCatcher(ErrorCodes errorCodes) {
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
