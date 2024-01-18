package com.simplon.labxpert.model.entity;

import com.simplon.labxpert.model.enums.ReagentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(name = "reagentSerialNumber", unique = true)
    private String reagentSerialNumber;
    @Column(name = "reagentName", unique = true)
    private String reagentName;
    @Column(name = "reagentDescription")
    private String reagentDescription;
    @Column(name = "quantityInStock")
    private int quantityInStock = 0;
    @Column(name = "expirationDate")
    private LocalDateTime expirationDate;
    @Enumerated(EnumType.STRING)
    private ReagentStatus reagentStatus;
    @Column(name = "supplier")
    private String supplier;
    @OneToMany(mappedBy = "reagent")
    private List<AnalysisReagent> analysisReagents;
}
