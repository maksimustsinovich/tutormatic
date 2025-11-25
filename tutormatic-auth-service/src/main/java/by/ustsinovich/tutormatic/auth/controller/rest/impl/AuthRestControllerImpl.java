package by.ustsinovich.tutormatic.auth.controller.rest.impl;

import by.ustsinovich.tutormatic.auth.controller.rest.AuthRestController;
import by.ustsinovich.tutormatic.auth.dto.AuthResponse;
import by.ustsinovich.tutormatic.auth.dto.LoginRequest;
import by.ustsinovich.tutormatic.auth.dto.RefreshRequest;
import by.ustsinovich.tutormatic.auth.dto.RegisterRequest;
import by.ustsinovich.tutormatic.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestControllerImpl implements AuthRestController {

    private final RegisterService registerService;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        registerService.register(request);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public AuthResponse refresh(RefreshRequest request) {
        return null;
    }

    @Override
    public void logout(String accessToken) {

    }

}
