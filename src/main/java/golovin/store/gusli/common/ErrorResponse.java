package golovin.store.gusli.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.Arrays;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private int code;
    private String message;
    private String exception;
    private String trace;
    private Instant timestamp;

    public ErrorResponse(Exception exception, int code, boolean traceEnabled) {
        this.code = code;
        this.message = exception.getMessage();
        this.exception = exception.getClass().getName();
        if (traceEnabled) {
            this.trace = Arrays.toString(exception.getStackTrace());
        }
        this.timestamp = Instant.now();
    }

}
