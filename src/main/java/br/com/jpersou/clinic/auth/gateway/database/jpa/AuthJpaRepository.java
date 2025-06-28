package br.com.jpersou.clinic.auth.gateway.database.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthJpaRepository extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByEmail(String email);
}
