package com.simplon.labxpert.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplon.labxpert.model.enums.ReagentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReagentDTO {
    @Positive
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Null(message = "reagentID is generated by the system")
    private long reagentID;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Null(message = "reagentSerialNumber is generated by the system")
    private String reagentSerialNumber;

    @NotBlank(message = "reagentName is Mandatory")
    private String reagentName;

    @NotBlank(message = "reagentName is Mandatory")
    private String reagentDescription;

    @NotBlank(message = "quantityInStock is Mandatory")
    @Pattern(regexp = "^[0-9]*$", message = "quantityInStock must be a number")
    private int quantityInStock;

    @NotBlank(message = "expirationDate is Mandatory")
    private LocalDateTime expirationDate;

    @NotBlank(message = "supplier is Mandatory")
    private String supplier;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Null(message = "reagentStatus is generated by the system")
    private ReagentStatus reagentStatus;
}
