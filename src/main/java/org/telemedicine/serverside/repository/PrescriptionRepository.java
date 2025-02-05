package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.Prescription;


import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
}
