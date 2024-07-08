package com.yugabyte.olympics.entity;

/**
 * Class for Spring JPA to project the aggregate medal count
 */
public interface MedalCount {

    public String getName();

    public String getTeam();

    public Integer getYear();

    public Integer getMedalCount();

    public String getMedal();

}
