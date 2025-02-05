package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.Examination;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, String> {
}
