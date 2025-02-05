package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String type;

    @Column(nullable = false, precision = 10, scale = 2) // precision: tổng số chữ số, scale: số chữ số sau dấu thập phân
    private BigDecimal price;

    @OneToMany(mappedBy = "serviceEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Examination> examinations = new ArrayList<>();
}
