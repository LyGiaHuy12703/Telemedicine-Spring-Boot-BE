package org.telemedicine.serverside.dto.register;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecordBookRequest {
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    String name;
    Boolean gender;
    LocalDate dob;
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    String address;
    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
    String phone;
    String bhyt;

}
