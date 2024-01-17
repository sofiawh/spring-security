package com.simplon.labxpert.model.dto;

import com.simplon.labxpert.model.entity.Sample;
import com.simplon.labxpert.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PatientDTO {
    @Positive
    private long patientID;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String patientEmail;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @NotBlank(message = "gender is required")
    private Gender gender;
    @NotBlank(message = "Address is required")
    private String address;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;
    private List<Sample> samples;
}