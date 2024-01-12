package com.simplon.labxpert.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "sample_id")
    private Sample sample;
    @ManyToMany
    @JoinTable(
            name = "analysis_reagent",
            joinColumns = @JoinColumn(name = "analysis_id"),
            inverseJoinColumns = @JoinColumn(name = "reagent_id")
    )
    private List<Reagent> reagents;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;
}
