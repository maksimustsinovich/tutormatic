package by.ustsinovich.tutormatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableJpaAuditing
@EnableMethodSecurity
@SpringBootApplication
public class TutormaticAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutormaticAuthServiceApplication.class, args);
    }

}
