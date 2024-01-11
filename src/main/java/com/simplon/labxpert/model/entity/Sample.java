package com.simplon.labxpert.model.entity;

import com.simplon.labxpert.model.enums.SampleStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "samples")
public class Sample {
    @Id
    @SequenceGenerator(
            name = "sample_id_sequence",
            sequenceName = "sample_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sample_id_sequence"
    )
    private long sampleID;
    @Column(name = "analysisType")
    private String analysisType;
    @Column(name = "sampleDescription")
    private String sampleDescription;
    @Column(name = "collectionDate")
    private LocalDate collectionDate;
    @Column(name = "sampleStatus")
    private SampleStatus sampleStatus;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @OneToMany(mappedBy = "sample")
    private List<Analysis> analyses;
}
