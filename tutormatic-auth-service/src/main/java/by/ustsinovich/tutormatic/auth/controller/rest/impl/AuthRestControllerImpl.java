package by.ustsinovich.tutormatic.auth.controller.rest.impl;

import by.ustsinovich.tutormatic.auth.controller.rest.AuthRestController;
import by.ustsinovich.tutormatic.auth.dto.AuthResponse;
import by.ustsinovich.tutormatic.auth.dto.LoginRequest;
import by.ustsinovich.tutormatic.auth.dto.RefreshRequest;
import by.ustsinovich.tutormatic.auth.dto.RegisterRequest;
import by.ustsinovich.tutormatic.auth.service.AuthService;
import by.ustsinovich.tutormatic.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestControllerImpl implements AuthRestController {

    private final RegisterService registerService;

    private final AuthService authService;

    @Override
    @Transactional
    public void register(final RegisterRequest request) {
        registerService.register(request);
    }

    @Override
    @Transactional
    public AuthResponse login(final LoginRequest request) {
        return authService.login(request);
    }

    @Override
    @Transactional
    public AuthResponse refresh(final RefreshRequest request) {
        return authService.refresh(request);
    }

    @Override
    @Transactional
    public void logout(String accessToken) {
        String username = authService.extractUsernameFromToken(accessToken.replace("Bearer ", ""));
        authService.logout(username);
    }

}
