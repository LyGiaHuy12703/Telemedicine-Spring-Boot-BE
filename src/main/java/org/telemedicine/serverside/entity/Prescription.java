package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String benh;

    @ManyToOne
    @JoinColumn(name = "examination_id")
    @JsonBackReference
    Examination examination;

    @ManyToMany(mappedBy = "prescriptions")
    List<Medicines> medicines = new ArrayList<>();
}
