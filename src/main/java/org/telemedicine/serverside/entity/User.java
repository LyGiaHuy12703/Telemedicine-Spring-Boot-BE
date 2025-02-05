package org.telemedicine.serverside.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.telemedicine.serverside.enums.Roles;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String email;
    String name;
    String password;
    Boolean isEnable;
    LocalDateTime createdAt;
    Roles role;

    @Column(nullable = true, unique = false)
    String googleId;
}
