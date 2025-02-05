package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.RoomSchedule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, String> {

}
