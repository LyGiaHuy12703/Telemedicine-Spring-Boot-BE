package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.MedicalRecordBook;
import org.telemedicine.serverside.entity.User;

import java.util.Optional;

@Repository
public interface MedicalRecordBookRepository extends JpaRepository<MedicalRecordBook, String> {
    MedicalRecordBook findByUserId(User userId);
}
