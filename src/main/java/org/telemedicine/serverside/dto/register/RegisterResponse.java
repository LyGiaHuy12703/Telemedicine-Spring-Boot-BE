package org.telemedicine.serverside.dto.register;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.telemedicine.serverside.enums.Roles;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    String id;
    String name;
    String email;
    Roles roles;
    LocalDateTime createdAt;
}
