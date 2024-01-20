package com.simplon.labxpert.controller;

import com.simplon.labxpert.model.dto.SchedulingDTO;
import com.simplon.labxpert.service.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for managing scheduling.
 * @Author Ayoub Ait Si Ahmad
 */
@RestController
@RequestMapping("/api/v1/schedulings")
public class SchedulingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final SchedulingService schedulingService;

    @Autowired
    public SchedulingController(SchedulingService schedulingService){
        this.schedulingService = schedulingService;
    }
    /**
     * This method allows to get all schedulings.
     * @return a list of all schedulings.
     */
    @GetMapping
    public ResponseEntity<List<SchedulingDTO>> getALlSchedulings(){
        LOGGER.info("Fetching all schedulings Controller");
        List<SchedulingDTO> schedulingDTOS = schedulingService.getAllSchedulings();
        return new ResponseEntity<>(schedulingDTOS, HttpStatus.OK);
    }

    /**
     * This method allows to get a scheduling by id.
     * @param id the id of the scheduling to get.
     * @return a scheduling.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingDTO> getSchedulingById(@PathVariable long id){
        LOGGER.info("Fetching Scheduling by id Controller");
        SchedulingDTO schedulingDTO = schedulingService.getSchedulingById(id);
        return new ResponseEntity<>(schedulingDTO,HttpStatus.OK);
    }

}
