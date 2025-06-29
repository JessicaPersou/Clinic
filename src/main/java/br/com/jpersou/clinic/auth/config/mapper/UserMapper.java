package br.com.jpersou.clinic.auth.config.mapper;

import br.com.jpersou.clinic.auth.adapters.request.RegisterRequest;
import br.com.jpersou.clinic.auth.adapters.request.AuthRequest;
import br.com.jpersou.clinic.auth.adapters.response.AuthResponse;
import br.com.jpersou.clinic.auth.gateway.database.jpa.UserEntity;
import br.com.jpersou.clinic.auth.domain.User;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

    User toDomain(RegisterRequest request);

    RegisterRequest toResponseRegister(User user);

    AuthResponse toResponse(User user);

    AuthRequest toRequest(User user);

    default Optional<User> toDomain(Optional<UserEntity> userEntityOptional) {
        return userEntityOptional.map(this::toDomain);
    }
}
