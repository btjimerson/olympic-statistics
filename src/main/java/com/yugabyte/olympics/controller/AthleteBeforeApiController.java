package com.yugabyte.olympics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yugabyte.olympics.entity.AthleteBefore;
import com.yugabyte.olympics.entity.MedalCount;
import com.yugabyte.olympics.repository.AthleteBeforeRepository;

/**
 * REST controller for the AthleteBefore entity
 */
@RestController
@RequestMapping(path = "/api/v1/athletesBefore")
public class AthleteBeforeApiController {

    @Autowired
    AthleteBeforeRepository athleteBeforeRepository;

    /**
     * Gets all athletes
     * 
     * @return A list of all athletes
     */
    @GetMapping("/")
    public List<AthleteBefore> getAllAthletes() {

        return athleteBeforeRepository.findAll().subList(0, 1000);
    }

    /**
     * Gets the top medalers in a given year
     * 
     * @return The top medalers in a given year
     */
    @GetMapping("/top")
    public List<MedalCount> getTopMedalers() {
        return athleteBeforeRepository.findTopMedalers();
    }

}
