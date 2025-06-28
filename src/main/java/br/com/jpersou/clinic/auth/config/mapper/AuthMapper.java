package br.com.jpersou.clinic.auth.config.mapper;

import br.com.jpersou.clinic.auth.adapters.request.AuthRegisterRequest;
import br.com.jpersou.clinic.auth.adapters.request.AuthRequest;
import br.com.jpersou.clinic.auth.adapters.response.AuthResponse;
import br.com.jpersou.clinic.auth.domain.Auth;
import br.com.jpersou.clinic.auth.gateway.database.jpa.AuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {
    AuthEntity toEntity(Auth auth);

    Auth toDomain(AuthEntity authEntity);

    Auth toDomain(AuthRegisterRequest request);

    AuthRegisterRequest toResponseRegister(Auth auth);

    AuthResponse toResponse(Auth auth);

    AuthRequest toRequest(Auth auth);
}
