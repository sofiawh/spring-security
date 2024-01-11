package com.simplon.labxpert.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "statisticalReports")
public class StatisticalReport {
    @Id
    @SequenceGenerator(
            name = "statisticalReport_id_sequence",
            sequenceName = "statisticalReport_id_sequence",
            allocationSize = 1
    )
    @javax.persistence.GeneratedValue(
            strategy = javax.persistence.GenerationType.SEQUENCE,
            generator = "statisticalReport_id_sequence"
    )
    private long statisticalReportID;
    private String reportName;
    private LocalDate reportType;
    private String reportPeriod;
}
