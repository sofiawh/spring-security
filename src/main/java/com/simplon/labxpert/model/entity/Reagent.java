package com.simplon.labxpert.model.entity;

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
@Table(name = "reagents")
public class Reagent {
    @Id
    @SequenceGenerator(
            name = "reagent_id_sequence",
            sequenceName = "reagent_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reagent_id_sequence"
    )
    private long reagentID;
    @Column(name = "reagentName")
    private String reagentName;
    @Column(name = "reagentDescription")
    private String reagentDescription;
    @Column(name = "quantityInStock")
    private int quantityInStock;
    @Column(name = "expirationDate")
    private LocalDate expirationDate;
    @Column(name = "supplier")
    private String supplier;
    @OneToMany(mappedBy = "reagent")
    private List<AnalysisReagent> analysisReagents;
}
