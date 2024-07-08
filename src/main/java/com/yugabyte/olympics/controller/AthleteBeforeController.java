package com.yugabyte.olympics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yugabyte.olympics.entity.MedalCount;
import com.yugabyte.olympics.repository.AthleteBeforeRepository;

/**
 * MVC controller for the AthleteBefore entity
 */
@Controller
@RequestMapping(path = "/athletesBefore")
public class AthleteBeforeController {

    @Autowired
    AthleteBeforeRepository athleteBeforeRepository;

    /**
     * Gets the top medalers in a given year
     * 
     * @return The top medalers in a given year
     */
    @GetMapping("/top")
    public String getTopMedalers(Model model) {

        Long startTime = System.currentTimeMillis();
        List<MedalCount> medalers = athleteBeforeRepository.findTopMedalers();
        Long endTime = System.currentTimeMillis();

        model.addAttribute("medalers", medalers);
        model.addAttribute("queryTime", endTime - startTime);

        return "index";
    }

}
