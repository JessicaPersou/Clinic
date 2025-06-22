package br.com.jpersou.clinic.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String resource, String field, String value) {
        this(resource + " not found with " + field + " = " + value);
    }

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, ErrorType.RESOURCE_NOT_FOUND, message);
    }

}
