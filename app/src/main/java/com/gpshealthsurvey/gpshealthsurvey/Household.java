package com.gpshealthsurvey.gpshealthsurvey;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Louis on 2/22/2015.
 */
public class Household implements Serializable {
    long houseId;
    String description;
    double latitude;
    double longitude;
    //BufferedImage pic;

    //Random number generator
    Random rand = new Random(5);

    public Household() {
    }

    public Household(String description, double latitude, double longitude){
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.houseId = (long) rand.nextInt(1000000);
    }
    public Household( double latitude, double longitude){
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.houseId = (long) rand.nextInt(1000000);
    }

    public long getHouseId() {
        return houseId;
    }
    public void setHouseId(long id) {
        this.houseId = id;
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
