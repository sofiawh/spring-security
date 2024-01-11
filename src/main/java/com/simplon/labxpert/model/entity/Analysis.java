package com.simplon.labxpert.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "analysis")
public class Analysis {
    @Id
    @SequenceGenerator(
            name = "analysis_id_sequence",
            sequenceName = "analysis_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "analysis_id_sequence"
    )
    private long analysisID;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @Column(name = "comments")
    private String comments;
    @OneToOne(mappedBy = "analysis")
    private Result result;


}
