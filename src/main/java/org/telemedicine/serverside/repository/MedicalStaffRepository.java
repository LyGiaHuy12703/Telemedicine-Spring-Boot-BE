package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.MedicalStaff;

@Repository
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, String> {
}
