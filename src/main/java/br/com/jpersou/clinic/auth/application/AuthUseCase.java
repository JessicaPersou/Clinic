package br.com.jpersou.clinic.auth.application;

import br.com.jpersou.clinic.auth.adapters.request.AuthRequest;
import br.com.jpersou.clinic.auth.adapters.request.RegisterRequest;
import br.com.jpersou.clinic.auth.adapters.response.AuthResponse;
import br.com.jpersou.clinic.auth.config.mapper.UserMapper;
import br.com.jpersou.clinic.auth.config.security.JwtUtil;
import br.com.jpersou.clinic.auth.gateway.database.jpa.UserEntity;
import br.com.jpersou.clinic.auth.gateway.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUseCase implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isBlank()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        var userEntity = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return User.builder()
            .username(userEntity.username())
            .password(userEntity.password())
            .authorities(userEntity.role().getAuthority())
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username já existe");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email já existe");
        }

        UserEntity userEntity = new UserEntity(
            request.username(),
            request.email(),
            passwordEncoder.encode(request.password()),
            request.role()
        );

        userEntity.setCreatedAt(LocalDateTime.now());
        var toDomain = userMapper.toDomain(userEntity);
        var userSaved = userRepository.save(toDomain);
        var userToGeneratedtoken = userMapper.toEntity(userSaved);

        String token = jwtUtil.generateToken(userToGeneratedtoken);

        return new AuthResponse(
            token,
            userEntity.getUsername(),
            userEntity.getEmail(),
            userEntity.getRole(),
            "Usuário registrado com sucesso"
        );
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userEntity);

        return new AuthResponse(
            token,
            userEntity.getUsername(),
            userEntity.getEmail(),
            userEntity.getRole(),
            "Login realizado com sucesso"
        );
    }
}
