package br.com.jpersou.clinic.shared.exceptions;

public record ValidationErrorDetail(
    String field,
    String message
) {

}