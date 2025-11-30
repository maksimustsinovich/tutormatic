package by.ustsinovich.tutormatic.auth.service;

import by.ustsinovich.tutormatic.auth.entity.RefreshToken;
import by.ustsinovich.tutormatic.auth.entity.UserCredentials;

import java.time.Instant;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(UserCredentials user);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUser(UserCredentials user);

    RefreshToken findByToken(String token);
}
