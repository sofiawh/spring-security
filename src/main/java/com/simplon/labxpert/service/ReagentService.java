package com.simplon.labxpert.service;

import com.simplon.labxpert.model.dto.ReagentDTO;
import com.simplon.labxpert.model.enums.ReagentStatus;

import java.util.List;

public interface ReagentService {
    List<ReagentDTO> getAllReagents();
    List<ReagentDTO> getAllReagentsByStatus(ReagentStatus status);
    List<ReagentDTO> getAllReagentsThatExpired();
    ReagentDTO getReagentById(long reagentID);
    ReagentDTO addReagent(ReagentDTO reagentDTO);
    ReagentDTO updateReagent(ReagentDTO reagentDTO, long reagentID);
    String deleteReagent(long reagentID);
    public void updateReagentStatus();
}
