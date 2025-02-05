//package org.telemedicine.serverside.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//import java.time.LocalDate;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class MedicalHistory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    String id;
//
//    @Column(length = 500) // Set a reasonable length for medical history information
//    String medicalHistoryInfo;
//
//    LocalDate date;
//
//
//    @ManyToOne
//    @JoinColumn(name = "medicalRecord_id")
//    @JsonBackReference
//    MedicalRecordBook medicalRecordBook;
//}
