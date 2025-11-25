package by.ustsinovich.tutormatic.auth.service.impl;

import by.ustsinovich.tutormatic.auth.dto.RegisterRequest;
import by.ustsinovich.tutormatic.auth.entity.UserCredentials;
import by.ustsinovich.tutormatic.auth.repository.UserCredentialsRepository;
import by.ustsinovich.tutormatic.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final PasswordEncoder passwordEncoder;

    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        final var userCredentials = UserCredentials
                .builder()
                .userId(UUID.randomUUID())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userCredentialsRepository.save(userCredentials);
    }

}
