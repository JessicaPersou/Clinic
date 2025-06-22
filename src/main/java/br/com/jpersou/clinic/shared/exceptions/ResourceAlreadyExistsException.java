package br.com.jpersou.clinic.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends ApiException {

    public ResourceAlreadyExistsException(String resource, String field, String value) {
        this(resource + " with the same " + field + "[" + value + "] already exists.");
    }

    public ResourceAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, ErrorType.RESOURCE_ALREADY_EXISTS, message);
    }

}
