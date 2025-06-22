package br.com.jpersou.clinic.patient.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import lombok.Builder;
import lombok.With;


@With
@Builder(toBuilder = true)
public record Patient(
    String id,
    String firstName,
    String lastName,
    String cpf,
    LocalDate birthDate,
    String professionalId,
    LocalDateTime createdAt
) {
    public boolean isAdult() {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }
}

