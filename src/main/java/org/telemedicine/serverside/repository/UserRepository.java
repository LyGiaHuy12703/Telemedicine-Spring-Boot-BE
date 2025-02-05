package org.telemedicine.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telemedicine.serverside.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmailAndGoogleIdIsNull(String email);
    Optional<User> findByGoogleId(String googleId);
    boolean existsByGoogleId(String googleId);
}
