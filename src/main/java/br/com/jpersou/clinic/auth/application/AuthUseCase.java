package br.com.jpersou.clinic.auth.application;

import br.com.jpersou.clinic.auth.adapters.request.AuthRequest;
import br.com.jpersou.clinic.auth.adapters.response.AuthResponse;
import br.com.jpersou.clinic.auth.config.security.JwtTokenProvider;
import br.com.jpersou.clinic.auth.domain.Auth;
import br.com.jpersou.clinic.auth.domain.Login;
import br.com.jpersou.clinic.auth.domain.Role;
import br.com.jpersou.clinic.auth.gateway.AuthRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUseCase {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

//    public Login authenticate(AuthRequest request) {
//        Auth auth = authRepository.findByEmail(request.email())
//            .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.password(), auth.password())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        UserDetails userDetails = User.builder()
//            .password(auth.password())
//            .username(auth.email())
//            .roles(auth.role().name())
//            .build();
//
//        String token = jwtTokenProvider.generateToken(userDetails);
//        return new Login(token, auth.email(), auth.role());
//    }
//
//    public String token(Auth auth) {
//        UserDetails userDetails = User.builder().password(auth.password())
//            .username(auth.email())
//            .roles(auth.role().name())
//            .build();
//
//        return jwtTokenProvider.generateToken(userDetails);
//    }

    public Auth registerUser(Auth auth) {
        if (auth == null) {
            throw new RuntimeException("Não pode ser cadastrado already in use");
        }

        auth.toBuilder()
            .name(auth.name())
            .role(Role.DOCTOR)
            .createdAt(LocalDateTime.now())
            .email(auth.email())
            .password(passwordEncoder.encode(auth.password())).build();

        authRepository.save(auth);

        return auth;
    }
}
