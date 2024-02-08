package dev.rajat.UserService.Controllers;

import dev.rajat.UserService.DTOs.CreateRoleRequestDTO;
import dev.rajat.UserService.Models.Role;
import dev.rajat.UserService.Services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequestDTO createRoleRequestDTO){
        Role role = roleService.createRole(createRoleRequestDTO.getName());
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
