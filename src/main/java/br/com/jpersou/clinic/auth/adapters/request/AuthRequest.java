package br.com.jpersou.clinic.auth.adapters.request;

import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record AuthRequest(
    String email,
    String password
) {
}
