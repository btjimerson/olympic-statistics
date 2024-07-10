package com.yugabyte.olympics.entity;

import lombok.Data;

/**
 * Composite primary key for AthleteBefore and AthleteAfter entities
 */
@Data
public class AthleteId {

    Integer id;
    Integer year;
    String event;
}
