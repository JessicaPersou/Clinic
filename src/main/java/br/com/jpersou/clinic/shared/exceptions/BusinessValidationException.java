package br.com.jpersou.clinic.shared.exceptions;

import org.springframework.http.HttpStatus;


public class BusinessValidationException extends ApiException {

    public BusinessValidationException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, ErrorType.BUSINESS, message);
    }
}