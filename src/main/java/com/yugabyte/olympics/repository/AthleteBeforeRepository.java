package com.yugabyte.olympics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yugabyte.olympics.entity.AthleteBefore;

/**
 * JPA repository the AthleteBefore entity
 */
public interface AthleteBeforeRepository extends JpaRepository<AthleteBefore, Integer> {

    /**
     * Finds athlete_after where event = ?1 and medal does not equal ?2
     * 
     * @param event The event to get medaling athlets for
     * @param medal Medal values to filter out
     * @return
     */
    public List<AthleteBefore> findByEventEqualsAndMedalNotOrderByYearDesc(String event, String medal);
}
