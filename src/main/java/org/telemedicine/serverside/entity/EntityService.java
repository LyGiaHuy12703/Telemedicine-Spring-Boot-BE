package org.telemedicine.serverside.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
    @Column(nullable = false, precision = 10, scale = 2) // precision: tổng số chữ số, scale: số chữ số sau dấu thập phân
    BigDecimal price;
}
