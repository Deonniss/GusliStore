package golovin.store.gusli.controller.exceptionHandler;

import golovin.store.gusli.common.ErrorResponse;
import golovin.store.gusli.logger.Log;
import golovin.store.gusli.logger.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Log log = LogFactory.api.get(CustomExceptionHandler.class);

    @Value("${response.error.trace.enabled}")
    private boolean traceEnabled;

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorResponse(exception, HttpStatus.BAD_REQUEST.value(), traceEnabled), HttpStatus.BAD_REQUEST);
    }
}
