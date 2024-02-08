package dev.rajat.UserService.Repositories;

import dev.rajat.UserService.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findSessionByTokenAndUser_Id(String token, Long id);
}
