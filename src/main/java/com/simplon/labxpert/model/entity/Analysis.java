package com.simplon.labxpert.model.entity;

import com.simplon.labxpert.model.enums.AnalysisStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Enumerated(EnumType.STRING)
    private AnalysisStatus analysisStatus = AnalysisStatus.NEED_SCHEDULING;
    @OneToOne(mappedBy = "analysis")
    private Result result;
    @ManyToOne
    @JoinColumn(name = "sample_id")
    private Sample sample;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "analysis")
    private List<AnalysisReagent> analysisReagents;

}
