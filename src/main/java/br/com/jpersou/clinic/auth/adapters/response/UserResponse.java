package br.com.jpersou.clinic.auth.adapters.response;

import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record UserResponse(
    Long id,
    String username,
    String email,
    String role
) {
}
