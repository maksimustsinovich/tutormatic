package by.ustsinovich.tutormatic.auth.service.impl;

import by.ustsinovich.tutormatic.auth.config.JwtConfig;
import by.ustsinovich.tutormatic.auth.enumeration.TokenClaims;
import by.ustsinovich.tutormatic.auth.enumeration.TokenType;
import by.ustsinovich.tutormatic.auth.service.JwtService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtConfig jwtConfig;

    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();

    @Override
    @Transactional
    public String generateAccessToken(final UserDetails userDetails) {
        final var claims = new HashMap<String, Object>();
        final var authorities = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        claims.put(TokenClaims.ROLES.getValue(), authorities);
        claims.put(TokenClaims.TYPE.getValue(), TokenType.ACCESS);

        final var issuedAt = Instant.now();
        final var expiration = Instant.now().plus(jwtConfig.getAccessTokenExpirationMillis(), ChronoUnit.MILLIS);

        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiration))
                .signWith(secretKey)
                .compact();
    }

    @Override
    @Transactional
    public String generateRefreshToken(final UserDetails userDetails) {
        final var claims = new HashMap<String, Object>();

        claims.put(TokenClaims.TYPE.getValue(), TokenType.REFRESH);

        final var issuedAt = Instant.now();
        final var expiration = Instant.now().plus(jwtConfig.getRefreshTokenExpirationMillis(), ChronoUnit.MILLIS);

        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiration))
                .signWith(secretKey)
                .compact();
    }

    @Override
    @Transactional
    public Boolean isValidToken(final String token) {
        return null;
    }

}
