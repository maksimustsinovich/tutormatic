package by.ustsinovich.tutormatic.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    Boolean isValidToken(String token);

    Boolean isRefreshToken(String token);

    String extractUsernameFromToken(String refreshToken);
    
    String extractUsername(String token);
    
    Boolean isTokenValid(String token, UserDetails userDetails);

}
