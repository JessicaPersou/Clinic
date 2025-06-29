package br.com.jpersou.clinic.auth.gateway;

import br.com.jpersou.clinic.auth.config.mapper.UserMapper;
import br.com.jpersou.clinic.auth.domain.User;
import br.com.jpersou.clinic.auth.gateway.database.jpa.UserJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UseRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        var authEntity = userJpaRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found for email: " + email));
        return Optional.of(userMapper.toDomain(authEntity));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var userEntity = userJpaRepository.findByUsername(username);
        return userMapper.toDomain(userEntity);
    }

    @Override
    public User save(User user) {
        var authEntity = userMapper.toEntity(user);
        var entitySaved = userJpaRepository.save(authEntity);
        return userMapper.toDomain(entitySaved);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}
