package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.telemedicine.serverside.enums.StatusSchedule;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    LocalDate appointmentDate;

    Time appointmentTime;

    LocalDate appointmentCreateDate;

//    @Enumerated(EnumType.STRING)
    StatusSchedule status;

    int orderNumber;

    @Size(max = 255, message = "Reason for visit cannot exceed 255 characters")
    String lyDoKham;

    @ManyToOne
    @JoinColumn(name = "ms_id")
    @JsonBackReference
    MedicalStaff medicalStaff;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @OneToOne(mappedBy = "medicalSchedule")
    @JsonManagedReference
    Examination examination
            ;}
