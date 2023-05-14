package golovin.store.gusli.controller.exceptionHandler;

import golovin.store.gusli.common.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @Value("${response.error.trace.enabled}")
    private boolean traceEnabled;

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorResponse(exception, HttpStatus.BAD_REQUEST.value(), traceEnabled), HttpStatus.BAD_REQUEST);
    }

}
