package com.example.nexus.Exceptions.handler;

import com.example.nexus.Exceptions.exceptiongeneric.ConstraintViolationExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.DataIntegrityViolationExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.EntityNotFondExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.ExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.ExpiredJwtExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.FileNotFoundExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.IOExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.IllegalArgumentExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.InvalidEntityExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.JsonProcessingExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.MessagingExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.NamingExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.NumberFormatExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.ParseExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.ScriptExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.SecurityExceptionCatcher;
import com.example.nexus.Exceptions.exceptiongeneric.UsernameNotFoundExceptionCatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFondExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFondExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);

        final HttpStatus notfound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(notfound.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, notfound);
    }

     @ExceptionHandler(JsonProcessingExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(JsonProcessingExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus conflict = HttpStatus.CONFLICT;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(conflict.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, conflict);
    }

    @ExceptionHandler(DataIntegrityViolationExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(DataIntegrityViolationExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus conflict = HttpStatus.CONFLICT;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(conflict.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, conflict);
    }

    @ExceptionHandler(InvalidEntityExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).errors(exception.getErrors()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(IllegalArgumentExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(IllegalArgumentExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(ScriptExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(ScriptExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(SecurityExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(SecurityExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.FORBIDDEN;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(MessagingExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(MessagingExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.FORBIDDEN;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(ParseExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(ParseExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(IOExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(IOExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(NumberFormatExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(NumberFormatExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(ExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(ExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(FileNotFoundExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(FileNotFoundExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(UsernameNotFoundExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(UsernameNotFoundExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.UNAUTHORIZED;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(NamingExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(NamingExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(ExpiredJwtExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(ExpiredJwtExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.UNAUTHORIZED;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }

    @ExceptionHandler(ConstraintViolationExceptionCatcher.class)
    public ResponseEntity<ErrorDto> handleException(ConstraintViolationExceptionCatcher exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        final HttpStatus badrequest = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorDto errorDto = ErrorDto.builder().code(exception.getErrorCodes()).httpCode(badrequest.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDto, badrequest);
    }
}
