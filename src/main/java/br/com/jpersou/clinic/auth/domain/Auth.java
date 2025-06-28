package br.com.jpersou.clinic.auth.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record Auth(
    String id,
    String name,
    String email,
    String password,
    Role role,
    LocalDateTime createdAt
) {
}
