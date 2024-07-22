package com.yugabyte.olympics.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yugabyte.olympics.repository.AthleteAfterRepository;
import com.yugabyte.olympics.repository.AthleteBeforeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;

@Controller
public class OlympicStatisticsController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    AthleteBeforeRepository athleteBeforeRepository;

    @Autowired
    AthleteAfterRepository athleteAfterRepository;

    /**
     * Index controller method
     * 
     * @param model   The model to use for the view
     * @param session The HTTP session to use
     * @return
     */
    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        List<String> eventList = getEventList();
        session.setAttribute("eventList", eventList);
        model.addAttribute("eventList", eventList);
        return "index";

    }

    /**
     * Gets a list of medaling athletes for an event
     * 
     * @param event   The event to get medaling athletes for
     * @param pkValue Which primary key (aka table) to use
     * @param session The HTTP session to use
     * @param model   The model to use for the view
     * @return
     */
    @PostMapping("/medalers")
    public String getMedalersForEvent(@RequestParam("event") String event, @RequestParam("pkValue") String pkValue,
            HttpSession session,
            Model model) {

        Long startTime = System.currentTimeMillis();
        if (pkValue.equals("id")) {
            model.addAttribute("medalers",
                    athleteBeforeRepository.findByEventEqualsAndMedalNotOrderByYearDesc(event, "NA"));
        } else {
            model.addAttribute("medalers",
                    athleteAfterRepository.findByEventEqualsAndMedalNotOrderByYearDesc(event, "NA"));
        }
        Long endTime = System.currentTimeMillis();

        if (session.getAttribute("eventList") == null) {
            session.setAttribute("eventList", getEventList());
        }
        model.addAttribute("eventList", session.getAttribute("eventList"));
        model.addAttribute("selectedEvent", event);
        model.addAttribute("partitionKey", pkValue);
        model.addAttribute("queryTime", endTime - startTime);

        return "index";
    }

    public void saveRecord(Principal principal) {
        myRecord.setCountry(principal.)
    }

    /**
     * Gets a list of existing events
     * 
     * @return A list of existing events
     */
    protected List<String> getEventList() {
        List<String> events = entityManager.createQuery("""
                    select distinct(event) from athlete_before
                    order by event asc
                """, String.class).getResultList();

        return events;
    }
}
