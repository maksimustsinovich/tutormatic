package by.ustsinovich.tutormatic.auth.service.impl;

import by.ustsinovich.tutormatic.auth.dto.AuthResponse;
import by.ustsinovich.tutormatic.auth.dto.LoginRequest;
import by.ustsinovich.tutormatic.auth.dto.RefreshRequest;
import by.ustsinovich.tutormatic.auth.entity.UserCredentials;
import by.ustsinovich.tutormatic.auth.service.AuthService;
import by.ustsinovich.tutormatic.auth.service.JwtService;
import by.ustsinovich.tutormatic.auth.service.RefreshTokenService;
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
    
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public AuthResponse login(final LoginRequest request) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        final var userDetails = (UserDetails) authentication.getPrincipal();
        final UserCredentials user = (UserCredentials) tutormaticUserDetailsService.loadUserByUsername(request.username());

        final var accessToken = jwtService.generateAccessToken(userDetails);
        final var refreshTokenEntity = refreshTokenService.createRefreshToken(user);
        final var refreshToken = refreshTokenEntity.getToken();

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional
    public AuthResponse refresh(final RefreshRequest request) {
        final var refreshTokenString = request.refreshToken();
        
        // Find the refresh token in the database
        final var refreshToken = refreshTokenService.findByToken(refreshTokenString);
        
        // Verify the token hasn't expired
        refreshTokenService.verifyExpiration(refreshToken);
        
        // Load user details
        final var userDetails = tutormaticUserDetailsService.loadUserByUsername(refreshToken.getUser().getUsername());

        final var newAccessToken = jwtService.generateAccessToken(userDetails);
        
        // Create a new refresh token
        final var newRefreshTokenEntity = refreshTokenService.createRefreshToken(refreshToken.getUser());
        final var newRefreshToken = newRefreshTokenEntity.getToken();

        return AuthResponse
                .builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    @Override
    public String extractUsernameFromToken(String token) {
        return jwtService.extractUsernameFromToken(token);
    }

    @Override
    @Transactional
    public void logout(String username) {
        // Load user details to get the user object
        UserCredentials user = (UserCredentials) tutormaticUserDetailsService.loadUserByUsername(username);
        // Delete all refresh tokens associated with the user
        refreshTokenService.deleteByUser(user);
    }

}
