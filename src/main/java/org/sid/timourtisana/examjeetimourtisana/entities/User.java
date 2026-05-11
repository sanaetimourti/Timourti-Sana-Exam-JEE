package org.sid.timourtisana.examjeetimourtisana.entities;

import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  // ← AJOUTER CETTE ANNOTATION
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public enum UserRole {
        ROLE_CLIENT,
        ROLE_EMPLOYE,
        ROLE_ADMIN
    }
}