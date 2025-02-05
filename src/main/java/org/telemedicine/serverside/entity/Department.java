package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    @ManyToMany(mappedBy = "departments", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    List<MedicalStaff> staffs = new ArrayList<>();
}
