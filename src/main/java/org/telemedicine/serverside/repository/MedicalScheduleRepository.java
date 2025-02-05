package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.MedicalSchedule;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalScheduleRepository extends JpaRepository<MedicalSchedule, String> {
    }
