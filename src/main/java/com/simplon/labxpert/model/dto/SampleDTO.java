package com.simplon.labxpert.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplon.labxpert.model.entity.Analysis;
import com.simplon.labxpert.model.entity.Patient;
import com.simplon.labxpert.model.enums.AnalysisType;
import com.simplon.labxpert.model.enums.SampleStatus;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class SampleDTO {
    @Positive
    private long sampleID;
    @NotNull(message = "Analysis type cannot be null")
    private AnalysisType analysisType;
    @NotBlank(message = "Sample description cannot be blank")
    private String sampleDescription;
    @PastOrPresent(message="date of collection must be on the present or the past")
    private LocalDate collectionDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private SampleStatus sampleStatus;
    @NotNull(message = "Patient is mandatory")
    private PatientDTO patientDTO;
    private List<Analysis> analyses;

    @Override
    public String toString() {
        return "SampleDTO{" +
                "sampleID=" + sampleID +
                ", analysisType=" + analysisType +
                ", sampleDescription='" + sampleDescription + '\'' +
                ", collectionDate=" + collectionDate +
                ", sampleStatus=" + sampleStatus +
                ", patient=" + patientDTO +
                ", analyses=" + analyses +
                '}';
    }
}
