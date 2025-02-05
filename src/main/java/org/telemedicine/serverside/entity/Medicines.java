package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Medicines {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(columnDefinition = "TEXT")
    String name;

    @Column(columnDefinition = "TEXT")
    String dangBaoChe;

    @Column(columnDefinition = "TEXT")
    String nhomThuoc_tacDung;

    @Column(columnDefinition = "TEXT")
    String chiDinh;

    @Column(columnDefinition = "TEXT")
    String chongChiDinh;

    @Column(columnDefinition = "TEXT")
    String thanTrong;

    @Column(columnDefinition = "TEXT")
    String tacDungKMongMuon;

    @Column(columnDefinition = "TEXT")
    String lieu_cachDung;

    @Column(columnDefinition = "TEXT")
    String chuYKhiSUDung;

    @Column(columnDefinition = "TEXT")
    String taiLieuThamKhao;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    @JsonBackReference
    Drug drug;

    @ManyToMany
    @JoinTable(
            name = "prescription_medicine",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "prescription_id")
    )
    List<Prescription> prescriptions = new ArrayList<>();
}
