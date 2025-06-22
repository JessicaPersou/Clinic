package br.com.jpersou.clinic.shared.handler;

import br.com.jpersou.clinic.shared.exceptions.ValidationErrorDetail;
import java.util.List;

public record ApiErrorResponse(
    String type,
    String message,
    List<ValidationErrorDetail> details
) {

}