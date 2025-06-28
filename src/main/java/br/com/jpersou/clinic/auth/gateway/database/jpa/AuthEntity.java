package br.com.jpersou.clinic.auth.gateway.database.jpa;

import br.com.jpersou.clinic.auth.domain.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTH_PROFESSIONALS")
public class AuthEntity {

    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 50)
    private Role role;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
