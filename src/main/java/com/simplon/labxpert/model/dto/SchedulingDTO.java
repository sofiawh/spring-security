package com.simplon.labxpert.model.dto;

import com.simplon.labxpert.model.enums.Priority;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
public class SchedulingDTO {
    @Positive(message = "schedulingID must be positive")
    private long schedulingID;

    @NotBlank(message = "startDateAndTime must not be blank")
    @Future(message = "startDateAndTime must be in the future")
    private LocalDate startDateAndTime;

    @NotBlank(message = "endDateAndTime must not be blank")
    @Future(message = "endDateAndTime must be in the future")
    private LocalDate endDateAndTime;

    @NotNull(message = "priority must not be null")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotBlank(message = "notes must not be blank")
    private String notes;

    @Positive(message = "userID must be positive")
    @NotNull(message = "userID must not be null")
    private long userID;

    @Positive(message = "analysisID must be positive")
    @NotNull(message = "analysisID must not be null")
    private long analysisId;
}