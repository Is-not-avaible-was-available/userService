package dev.rajat.UserService.Repositories;

import dev.rajat.UserService.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public List<Role> findAllByIdIn(List<Long> ids);
}
