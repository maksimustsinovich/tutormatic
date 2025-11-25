package by.ustsinovich.tutormatic.auth.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {

}
