package br.com.jpersou.clinic.auth.gateway;

import br.com.jpersou.clinic.auth.config.mapper.AuthMapper;
import br.com.jpersou.clinic.auth.domain.Auth;
import br.com.jpersou.clinic.auth.gateway.database.jpa.AuthJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthJpaRepository authJpaRepository;
    private final AuthMapper authMapper;

    @Override
    public Optional<Auth> findByEmail(String email) {
        var authEntity = authJpaRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found for email: " + email));
        return Optional.of(authMapper.toDomain(authEntity));
    }

    @Override
    public Auth save(Auth auth) {
        var authEntity = authMapper.toEntity(auth);
        var entitySaved = authJpaRepository.save(authEntity);
        return authMapper.toDomain(entitySaved);
    }
}
