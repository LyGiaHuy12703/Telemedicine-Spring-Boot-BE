package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.Drug;

@Repository
public interface DrugsRepository extends JpaRepository<Drug, String> {
}
