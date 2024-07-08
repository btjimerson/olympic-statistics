package com.yugabyte.olympics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugabyte.olympics.entity.AthleteBefore;
import com.yugabyte.olympics.entity.MedalCount;

/**
 * JPA repository the AthleteBefore entity
 */
public interface AthleteBeforeRepository extends JpaRepository<AthleteBefore, Integer> {

    /**
     * Finds top medalers for a given year
     * 
     * @return The top medalers for a given year
     */
    @Query("select ab.name as name, ab.team as team, ab.year as year, count(ab.medal) as medalCount, " +
            "ab.medal as medal from athlete_before as ab where ab.medal <> 'NA' group by " +
            "(ab.medal, ab.team, ab.name, ab.year) order by medalCount desc limit 200")
    public List<MedalCount> findTopMedalers();

}
