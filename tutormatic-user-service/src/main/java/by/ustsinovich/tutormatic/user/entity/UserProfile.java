package by.ustsinovich.tutormatic.user.entity;

import by.ustsinovich.tutormatic.user.entity.embeddable.ContactInfo;
import by.ustsinovich.tutormatic.user.entity.embeddable.PersonalInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_profiles", schema = "users")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, updatable = false, unique = true)
    private UUID userId;

    @Embedded
    private PersonalInfo personalInfo;

    @Embedded
    private ContactInfo contactInfo;

}
