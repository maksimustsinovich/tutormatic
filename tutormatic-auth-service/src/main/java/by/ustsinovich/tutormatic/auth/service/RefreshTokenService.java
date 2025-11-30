package by.ustsinovich.tutormatic.auth.service;

import by.ustsinovich.tutormatic.auth.entity.RefreshToken;
import by.ustsinovich.tutormatic.auth.entity.UserCredentials;
import by.ustsinovich.tutormatic.auth.entity.UserPrincipal;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(UserPrincipal user);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUser(UserCredentials user);

    RefreshToken findByToken(String token);
}
