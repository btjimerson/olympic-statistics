package com.yugabyte.olympics.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugabyte.olympics.entity.AthleteAfter;

/**
 * JPA repository the AthleteAfter entity
 */
public interface AthleteAfterRepository extends JpaRepository<AthleteAfter, Integer> {

    /**
     * Finds athlete_after where event = ?1 and medal does not equal ?2
     * 
     * @param event The event to get medaling athletes for
     * @param medal Medal values to filter out
     * @return A list of AthleteAfter that medaled for the event
     */
    public List<AthleteAfter> findByEventEqualsAndMedalNotOrderByYearDesc(String event, String medal);

    /**
     * Gets the explain plan for find athlete_after where event = ?1 and medal does
     * not equal ?2
     * 
     * @param event The event to get medaling athlets for
     * @param medal Medal values to filter out
     * @return The explain plan for the query
     */
    @Query(value = "EXPLAIN ANALYZE SELECT * FROM athlete_after WHERE event = ?1 and medal <> ?2", nativeQuery = true)
    public List<String> explainFindByEventAndMedal(String event, String medal);

}
