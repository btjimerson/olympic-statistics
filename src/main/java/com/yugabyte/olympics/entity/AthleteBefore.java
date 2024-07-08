package com.yugabyte.olympics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

/**
 * Entity class for the athlete_before table
 */
@Data
@Entity(name = "athlete_before")
@IdClass(AthleteId.class)
public class AthleteBefore {

    @Id
    @Column(nullable = false)
    Integer id;

    @Id
    @Column(nullable = false)
    String event;

    @Id
    @Column(nullable = false)
    Integer year;

    @Column
    String name;

    @Column(length = 1)
    String sex;

    @Column(length = 25)
    String age;

    @Column(length = 25)
    String height;

    @Column(length = 25)
    String weight;

    @Column
    String team;

    @Column(length = 3)
    String noc;

    @Column(length = 15)
    String games;

    @Column()
    String city;

    @Column()
    String sport;

    @Column(length = 8)
    String medal;

}
