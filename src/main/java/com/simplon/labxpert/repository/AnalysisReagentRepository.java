package com.simplon.labxpert.repository;

import com.simplon.labxpert.model.entity.AnalysisReagent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisReagentRepository extends JpaRepository<AnalysisReagent, Long>{
}
