package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
}
