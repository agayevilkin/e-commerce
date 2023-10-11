package com.example.elcstore.dto.response;

import com.example.elcstore.domain.enums.UserAccountStateStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserResponseDto {

    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String userDescription;
    private String profilePic;
    private UserAccountStateStatus userAccountStateStatus;
    //todo change with DTO
//    private List<Role> roles;
}
