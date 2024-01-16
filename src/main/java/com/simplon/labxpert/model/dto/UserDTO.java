package com.simplon.labxpert.model.dto;

import com.simplon.labxpert.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long userID;
    private String email;
    private Boolean isEmailVerified;
    private String username;
    private String password;
    private UserRole userRole;
    private String personalInfo;
}
