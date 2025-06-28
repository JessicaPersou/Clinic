package br.com.jpersou.clinic.auth.adapters.response;

import br.com.jpersou.clinic.auth.domain.Role;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record AuthResponse(
    String token,
    String email,
    Role role,
    LocalDateTime createdAt
) {
}
