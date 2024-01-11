package com.simplon.labxpert.repository;

import com.simplon.labxpert.model.entity.StatisticalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticalReportRepository extends JpaRepository<StatisticalReport, Long> {
}
