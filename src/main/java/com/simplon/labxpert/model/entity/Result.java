package com.simplon.labxpert.model.entity;

import com.simplon.labxpert.model.enums.ResultStatus;
import com.simplon.labxpert.model.entity.Analysis;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Results")
public class Result {

    @Id
    @SequenceGenerator(
            name = "result_id_sequence",
            sequenceName = "result_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "result_id_sequence"
    )
    @Column(name = "resultID")
    private long resultID;
    @Column(name = "resultValues")
    private double resultValues;
    @Column(name = "measurementUnits")
    private String measurementUnits;
    @Column(name = "resultStatus")
    private ResultStatus resultStatus;

}
