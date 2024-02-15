package dev.rajat.UserService.SpringSecurity.Repositories;

import java.util.Optional;

import dev.rajat.UserService.SpringSecurity.Models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}