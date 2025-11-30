package by.ustsinovich.tutormatic.auth.service.impl;

import by.ustsinovich.tutormatic.auth.dto.AuthResponse;
import by.ustsinovich.tutormatic.auth.dto.LoginRequest;
import by.ustsinovich.tutormatic.auth.dto.RefreshRequest;
import by.ustsinovich.tutormatic.auth.exception.InvalidRefreshTokenException;
import by.ustsinovich.tutormatic.auth.service.AuthService;
import by.ustsinovich.tutormatic.auth.service.JwtService;
import by.ustsinovich.tutormatic.auth.service.TutormaticUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final TutormaticUserDetailsService tutormaticUserDetailsService;

    @Override
    @Transactional
    public AuthResponse login(final LoginRequest request) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        final var userDetails = (UserDetails) authentication.getPrincipal();

        final var accessToken = jwtService.generateAccessToken(userDetails);
        final var refreshToken = jwtService.generateRefreshToken(userDetails);

        return AuthResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional
    public AuthResponse refresh(final RefreshRequest request) {
        final var refreshToken = request.refreshToken();

        if (!jwtService.isValidToken(refreshToken) || !jwtService.isRefreshToken(refreshToken)) {
            throw new InvalidRefreshTokenException();
        }

        final var username = jwtService.extractUsernameFromToken(refreshToken);



        return AuthResponse
                .builder()
                .build();
    }

}
