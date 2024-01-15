package com.simplon.labxpert.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tests")
public class Test {
    @Id
    @SequenceGenerator(
            name = "test_id_sequence",
            sequenceName = "test_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "test_id_sequence"
    )
    private long testID;
    @Column(name = "nameTest")
    private String nameTest;
    @Column(name = "maxValue")
    private double maxValue;
    @Column(name = "minValue")
    private double minValue;
    @Column(name = "measurementUnits")
    private String measurementUnits;
    @OneToMany(mappedBy = "test")
    private List<TestResult> testResult;

}
