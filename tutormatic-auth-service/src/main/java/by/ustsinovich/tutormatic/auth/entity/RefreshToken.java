package by.ustsinovich.tutormatic.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", nullable = false, updatable = false, unique = true)
    private Long tokenId;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserCredentials user;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

}