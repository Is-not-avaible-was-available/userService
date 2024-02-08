package dev.rajat.UserService.Controllers;

import dev.rajat.UserService.DTOs.SetUserRolesRequestDTO;
import dev.rajat.UserService.DTOs.UserDto;
import dev.rajat.UserService.Exceptions.NotFoundException;
import dev.rajat.UserService.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController{

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long userId) throws NotFoundException {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id") Long userId,
                                                @RequestBody SetUserRolesRequestDTO requestDTO) throws NotFoundException {
        UserDto userDto = userService.setUserRoles(userId, requestDTO.getRoleIds());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
