package br.com.jpersou.clinic.shared.exceptions;

import static java.util.Collections.emptyList;

import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final List<ValidationErrorDetail> details;
    private final ErrorType type;

    public ApiException(HttpStatus status, ErrorType type, String message) {
        this(status, message, type, emptyList(), null);
    }

    public ApiException(HttpStatus status, ErrorType type, String message, List<ValidationErrorDetail> details) {
        this(status, message, type, details, null);
    }

    public ApiException(HttpStatus status, String message, ErrorType type, List<ValidationErrorDetail> details,
                        Throwable throwable) {
        super(message, throwable);
        this.status = status;
        this.type = type;
        this.details = details;
    }

}