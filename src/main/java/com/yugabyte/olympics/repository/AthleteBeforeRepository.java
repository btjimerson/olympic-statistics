package com.yugabyte.olympics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugabyte.olympics.entity.AthleteBefore;

/**
 * JPA repository the AthleteBefore entity
 */
public interface AthleteBeforeRepository extends JpaRepository<AthleteBefore, Integer> {

    /**
     * Finds athlete_before where event = ?1 and medal does not equal ?2
     * 
     * @param event The event to get medaling athlets for
     * @param medal Medal values to filter out
     * @return A list of AthleteBefore that medaled for the event
     */
    public List<AthleteBefore> findByEventEqualsAndMedalNotOrderByYearDesc(String event, String medal);

    /**
     * Gets the explain plan for find athlete_before where event = ?1 and medal does
     * not equal ?2
     * 
     * @param event The event to get medaling athlets for
     * @param medal Medal values to filter out
     * @return The explain plan for the query
     */
    @Query(value = "EXPLAIN ANALYZE SELECT * FROM athlete_before WHERE event = ?1 and medal <> ?2", nativeQuery = true)
    public List<String> explainFindByEventAndMedal(String event, String medal);
}
