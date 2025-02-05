package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    LocalDateTime examinationDate; // Changed to LocalDateTime for date and time

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    @JsonBackReference
    ServiceEntity serviceEntity;

    @ManyToOne
    @JoinColumn(name = "room_schedule_id", nullable = false)
    @JsonBackReference
    RoomSchedule roomSchedule;

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Prescription> prescriptions = new ArrayList<>();

    @OneToOne @JoinColumn(name = "medical_schedule_id", nullable = false)
    @JsonBackReference
    MedicalSchedule medicalSchedule;
}
