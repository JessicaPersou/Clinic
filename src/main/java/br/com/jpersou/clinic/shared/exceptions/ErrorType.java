package br.com.jpersou.clinic.shared.exceptions;

import lombok.Getter;

@Getter
public enum ErrorType {

    BUSINESS("BusinessError"),
    VALIDATION("ValidationError"),
    RESOURCE_NOT_FOUND("ResourceNotFound"),
    RESOURCE_ALREADY_EXISTS("ResourceAlreadyExists"),
    INTERNAL_SERVER_ERROR("InternalError");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

}