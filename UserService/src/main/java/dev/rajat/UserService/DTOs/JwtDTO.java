package dev.rajat.UserService.DTOs;

import dev.rajat.UserService.Models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtDTO {
    private SessionStatus sessionStatus;
    private String email;
    private String roles;
}
