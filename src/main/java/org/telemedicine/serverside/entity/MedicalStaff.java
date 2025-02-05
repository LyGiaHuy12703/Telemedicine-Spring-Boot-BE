package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.telemedicine.serverside.enums.Roles;
import org.telemedicine.serverside.enums.Status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    String name;

    @Email(message = "Email should be valid")
    String email;

    String password;

    boolean gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dob;

    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
    String phone;

    @Size(max = 255, message = "Address cannot exceed 255 characters")
    String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    String practicingCertificate;

    @Enumerated(EnumType.STRING)
    Status status;

    String avatar;
    boolean isEnabled = true;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<Roles> roles = new HashSet<>(); // Unique roles in set
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    Set<String> hocHam = new HashSet<>();
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    Set<String> hocVi = new HashSet<>();


    @OneToMany(mappedBy = "medicalStaff", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<RoomSchedule> roomSchedules;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "medical_staff_department",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonBackReference
    Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "medicalStaff", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<MedicalSchedule> medicalSchedules;
}
