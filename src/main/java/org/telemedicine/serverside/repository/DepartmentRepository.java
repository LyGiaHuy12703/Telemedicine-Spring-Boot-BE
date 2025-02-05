package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telemedicine.serverside.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
