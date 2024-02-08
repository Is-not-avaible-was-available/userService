package dev.rajat.UserService.Services;

import dev.rajat.UserService.DTOs.UserDto;
import dev.rajat.UserService.Exceptions.NotFoundException;
import dev.rajat.UserService.Models.Role;
import dev.rajat.UserService.Models.User;
import dev.rajat.UserService.Repositories.RoleRepository;
import dev.rajat.UserService.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    public UserService(UserRepository userRepository, RoleRepository repository){
        this.roleRepository = repository;
        this.userRepository = userRepository;
    }

    public UserDto getUserById(Long id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with id:" + id + ", is not found!");
        }
        User user = optionalUser.get();
        return UserDto.from(user);
    }

    public UserDto setUserRoles(Long id, List<Long> roleIds) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("User with id:"+id+", is not found!");
        }

        User user = optionalUser.get();
        List<Role> roles = roleRepository.findAllByIdIn(roleIds);
        user.setRoles(Set.copyOf(roles));

        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }
}