package by.ustsinovich.tutormatic.auth.service.impl;

import by.ustsinovich.tutormatic.auth.entity.UserCredentials;
import by.ustsinovich.tutormatic.auth.entity.UserPrincipal;
import by.ustsinovich.tutormatic.auth.repository.UserCredentialsRepository;
import by.ustsinovich.tutormatic.auth.service.TutormaticUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TutormaticUserDetailsServiceImpl implements TutormaticUserDetailsService {

    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials user = userCredentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        return UserPrincipal.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

}
