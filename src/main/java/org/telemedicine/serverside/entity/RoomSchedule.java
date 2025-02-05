package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    LocalDate date;

    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false)
    @JsonBackReference
    Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "ms_id", nullable = false)
    @JsonBackReference
    MedicalStaff medicalStaff;

    @OneToMany(mappedBy = "roomSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Examination> examination;
}
