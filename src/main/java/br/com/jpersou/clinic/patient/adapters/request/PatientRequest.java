package br.com.jpersou.clinic.patient.adapters.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record PatientRequest(
    String firstName,
    String lastName,
    String cpf,
    LocalDate birthDate,
    String professionalId,
    LocalDateTime createdAt
) {
}
