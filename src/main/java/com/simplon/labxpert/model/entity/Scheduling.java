package com.simplon.labxpert.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedulings")
public class Scheduling {
    @Id
    @SequenceGenerator(
            name = "scheduling_id_sequence",
            sequenceName = "scheduling_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "scheduling_id_sequence"
    )
    private long schedulingID;
    @Column(name = "startDateAndTime")
    private LocalDate startDateAndTime;
    @Column(name = "endDateAndTime")
    private LocalDate endDateAndTime;

}
