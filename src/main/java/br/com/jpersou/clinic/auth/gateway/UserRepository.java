package br.com.jpersou.clinic.auth.gateway;

import br.com.jpersou.clinic.auth.domain.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    User save(User user);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
