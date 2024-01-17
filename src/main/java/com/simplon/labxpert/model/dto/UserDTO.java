package com.simplon.labxpert.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.simplon.labxpert.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.*;

@Getter
@Setter
public class UserDTO {

    private long userID;
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;
    @JsonIgnore
    private Boolean isEmailVerified;
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one digit, one lowercase, one uppercase and one special character")
    private String password;
    @NotNull(message = "User role is mandatory")
    private UserRole userRole;
    @NotBlank(message = "Personal info is mandatory")
    private String personalInfo;
}