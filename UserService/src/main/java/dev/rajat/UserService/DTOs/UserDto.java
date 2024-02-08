package dev.rajat.UserService.DTOs;

import dev.rajat.UserService.Models.Role;
import dev.rajat.UserService.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String email;
    private List<Role> roles;

    public static UserDto from(User user){
        UserDto userDto  = new UserDto();
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
