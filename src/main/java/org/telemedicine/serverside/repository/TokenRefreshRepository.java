package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.TokenRefresh;

@Repository
public interface TokenRefreshRepository extends JpaRepository<TokenRefresh, String> {
}
