package br.com.jpersou.clinic.auth.gateway;

import br.com.jpersou.clinic.auth.domain.Auth;
import java.util.Optional;

public interface AuthRepository {
    Optional<Auth> findByEmail(String email);
    Auth save(Auth auth);
}
