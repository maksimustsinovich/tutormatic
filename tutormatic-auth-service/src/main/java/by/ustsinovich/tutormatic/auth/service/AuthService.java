package by.ustsinovich.tutormatic.auth.service;

import by.ustsinovich.tutormatic.auth.dto.AuthResponse;
import by.ustsinovich.tutormatic.auth.dto.LoginRequest;
import by.ustsinovich.tutormatic.auth.dto.RefreshRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse refresh(RefreshRequest request);
    
    String extractUsernameFromToken(String token);
    
    void logout(String username);

}
