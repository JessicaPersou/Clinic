package br.com.jpersou.clinic.auth.adapters.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record AuthRequest(
    @NotBlank(message = "Username é obrigatório")
    String username,

    @NotBlank(message = "Password é obrigatório")
    @Size(min = 6, message = "Password deve ter no mínimo 6 caracteres")
    String password
) {
}
