package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, String> {
}
