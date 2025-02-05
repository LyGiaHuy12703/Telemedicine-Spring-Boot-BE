package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordBook {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    String name;

    Boolean gender;

    @Temporal(TemporalType.DATE) // Ensures only the date is stored, not the time
    LocalDate dob;

    @Size(max = 255, message = "Address cannot exceed 255 characters")
    String address;

    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
    String phone;

    String bhyt; // Optional: Can add validation if needed

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User userId;

    String avatar;

//    @OneToMany(mappedBy = "medicalRecordBook", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    List<MedicalHistory> medicalHistory;
}
