package com.gpshealthsurvey.gpshealthsurvey;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Louis on 2/22/2015.
 */
public class Household implements Serializable {
    long houseId;
    //String uID;
    String description;
    double latitude;
    double longitude;
    String villageName;
    String surveyXML;
    int randomNum;

    //BufferedImage pic;

    //Random number generator
    private Random rand = new Random();
    //private Installation installation = new Installation();

    public Household() {
    }

    public Household(String description, double latitude, double longitude){
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.randomNum = rand.nextInt(1000000);
        //this.uID = Installation.id(this.getApplicationContext());

    }
    public Household( double latitude, double longitude){
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.randomNum = rand.nextInt(1000000);
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
    public String getVillageName() {return villageName;}
    public void setVillageName(String _villageName) {this.villageName = _villageName;}
    public String getSurveyXML() {return surveyXML;}
    public void setSurveyXML(String _surveyXML) {this.surveyXML = _surveyXML;}
    public int getRandomNum() {return randomNum;}
    public void setRandomNum(int _randomNum) {this.randomNum = _randomNum;}
    //public String getUniqueID() {return uID;}
    //public void setUniqueID(String _uID) {this.uID = _uID;}
}
