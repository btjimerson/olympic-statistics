package com.yugabyte.olympics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugabyte.olympics.entity.AthleteAfter;
import com.yugabyte.olympics.entity.MedalCount;

/**
 * JPA repository the AthleteAfter entity
 */
public interface AthleteAfterRepository extends JpaRepository<AthleteAfter, Integer> {

    /**
     * Finds top medalers for a given year
     * 
     * @return The top medalers for a given year
     */
    @Query("select aa.name as name, aa.team as team, aa.year as year, count(aa.medal) as medalCount, " +
            "aa.medal as medal from athlete_after as aa where aa.medal <> 'NA' group by " +
            "(aa.medal, aa.team, aa.name, aa.year) order by medalCount desc limit 200")
    public List<MedalCount> findTopMedalers();

}
