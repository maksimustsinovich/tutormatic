package by.ustsinovich.tutormatic.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class JwtConfig {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.access-token-expiration}")
    private Long accessTokenExpirationMillis;

    @Value("${security.jwt.refresh-token-expiration}")
    private Long refreshTokenExpirationMillis;

}
