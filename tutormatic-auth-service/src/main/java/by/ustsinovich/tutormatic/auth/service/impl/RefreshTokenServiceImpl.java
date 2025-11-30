package by.ustsinovich.tutormatic.auth.service.impl;

import by.ustsinovich.tutormatic.auth.entity.RefreshToken;
import by.ustsinovich.tutormatic.auth.entity.UserCredentials;
import by.ustsinovich.tutormatic.auth.exception.InvalidRefreshTokenException;
import by.ustsinovich.tutormatic.auth.repository.RefreshTokenRepository;
import by.ustsinovich.tutormatic.auth.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public RefreshToken createRefreshToken(UserCredentials user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(604800)) // 7 days
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    @Transactional
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new InvalidRefreshTokenException();
        }
        return token;
    }

    @Override
    @Transactional
    public void deleteByUser(UserCredentials user) {
        refreshTokenRepository.deleteByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidRefreshTokenException());
    }
}
