package br.com.jpersou.clinic.shared.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ValidationException extends ApiException {

    public ValidationException(List<ValidationErrorDetail> details) {
        this("There are validation errors in the submitted data", details);
    }

    public ValidationException(String message, List<ValidationErrorDetail> details) {
        super(HttpStatus.BAD_REQUEST, ErrorType.VALIDATION, message, details);
    }

}
