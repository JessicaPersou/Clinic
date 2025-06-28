package br.com.jpersou.clinic.auth.adapters;

import br.com.jpersou.clinic.auth.adapters.request.AuthRegisterRequest;
import br.com.jpersou.clinic.auth.application.AuthUseCase;
import br.com.jpersou.clinic.auth.config.mapper.AuthMapper;
import br.com.jpersou.clinic.auth.domain.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthUseCase authUseCase;
    private final AuthMapper authMapper;

    @PostMapping("/register")
    public AuthRegisterRequest generateToken(@RequestBody AuthRegisterRequest authRequest) {
        Auth auth = authMapper.toDomain(authRequest);
        Auth response = authUseCase.registerUser(auth);
        return authMapper.toResponseRegister(response);
    }

//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//        AuthResponse response = authUseCase.authenticate(request);
//        return ResponseEntity.ok(response);
//    }
}