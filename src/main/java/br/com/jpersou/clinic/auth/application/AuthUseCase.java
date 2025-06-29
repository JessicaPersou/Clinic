package br.com.jpersou.clinic.auth.application;

import br.com.jpersou.clinic.auth.adapters.request.AuthRequest;
import br.com.jpersou.clinic.auth.adapters.request.RegisterRequest;
import br.com.jpersou.clinic.auth.adapters.response.AuthResponse;
import br.com.jpersou.clinic.auth.config.mapper.UserMapper;
import br.com.jpersou.clinic.auth.config.security.JwtService;
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
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isBlank()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        var userDomain = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return User.builder()
            .username(userDomain.username())
            .password(userDomain.password())
            .authorities(userDomain.role().getAuthority())
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username já existe");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email já existe");
        }

        // Criar o domain object diretamente
        var userDomain = userMapper.toDomain(request)
            .withPassword(passwordEncoder.encode(request.password()));

        var userSaved = userRepository.save(userDomain);

        // Converter para UserDetails para gerar token
        UserDetails userDetails = User.builder()
            .username(userSaved.username())
            .password(userSaved.password())
            .authorities(userSaved.role().getAuthority())
            .build();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(
            token,
            userSaved.username(),
            userSaved.email(),
            userSaved.role(),
            "Usuário registrado com sucesso"
        );
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        // Buscar dados completos do usuário
        var userDomain = userRepository.findByUsername(request.username())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new AuthResponse(
            token,
            userDomain.username(),
            userDomain.email(),
            userDomain.role(),
            "Login realizado com sucesso"
        );
    }
}