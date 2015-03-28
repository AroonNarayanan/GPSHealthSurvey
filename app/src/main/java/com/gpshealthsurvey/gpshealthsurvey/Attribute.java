package com.gpshealthsurvey.gpshealthsurvey;

import android.provider.BaseColumns;

/**
 * Created by Louis on 2/22/2015.
 */
public final class Attribute {
    private long id;
    private String name;
    private String type;
    private String value;
    private int surveyId;
    private int householdId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public int getSurveyId() {
        return surveyId;
    }
    public void setSurveyId(int id) {
        this.surveyId = id;
    }
    public int getHouseholdId() {
        return householdId;
    }
    public void setHouseholdId(int id) {
        this.householdId = id;
    }
    public String toString() {
        return name;
    }
}
