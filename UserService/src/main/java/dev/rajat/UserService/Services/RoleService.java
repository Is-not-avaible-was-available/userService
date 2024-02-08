package dev.rajat.UserService.Services;

import dev.rajat.UserService.DTOs.UserDto;
import dev.rajat.UserService.Models.Role;
import dev.rajat.UserService.Repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    public RoleService(RoleRepository repository){
        this.roleRepository = repository;
    }
    public Role createRole(String name){
        Role role = new Role();
        role.setRole(name);

        return roleRepository.save(role);
    }
}
