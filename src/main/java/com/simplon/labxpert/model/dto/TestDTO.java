package com.simplon.labxpert.model.dto;

import com.simplon.labxpert.model.entity.TestResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestDTO {

    private long testID;
    private String nameTest;
    private double maxValue;
    private double minValue;
    private String measurementUnits;
    private List<TestResult> testResult;
}
