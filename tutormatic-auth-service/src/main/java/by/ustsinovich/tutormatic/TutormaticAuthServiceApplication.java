package by.ustsinovich.tutormatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class TutormaticAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutormaticAuthServiceApplication.class, args);
    }

}
