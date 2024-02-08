package dev.rajat.UserService.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRolesRequestDTO {
    private List<Long> roleIds;
}
