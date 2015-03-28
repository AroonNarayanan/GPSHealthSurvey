package com.gpshealthsurvey.gpshealthsurvey;

/**
 * Created by Louis on 2/22/2015.
 */
public class Project {
    private long projectID;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    //BufferedImage img;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getProjectId() {
        return projectID;
    }
    public void setProjectId(long projectId) {
        this.projectID = projectID;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double lat) {
        this.latitude = lat;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
