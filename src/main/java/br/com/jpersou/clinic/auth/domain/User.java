package br.com.jpersou.clinic.auth.domain;

import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record User(
    String username,
    String email,
    String password,
    Role role
) {
}
