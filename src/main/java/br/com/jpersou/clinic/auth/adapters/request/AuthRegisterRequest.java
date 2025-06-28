package br.com.jpersou.clinic.auth.adapters.request;

import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record AuthRegisterRequest(
    String name,
    String email,
    String role,
    String password
) {
}
