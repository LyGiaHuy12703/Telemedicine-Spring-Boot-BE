package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.ServiceEntity;


@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, String> {
    boolean existsByType(String type);
}
