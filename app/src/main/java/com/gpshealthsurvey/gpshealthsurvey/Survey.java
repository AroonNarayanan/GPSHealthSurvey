package com.gpshealthsurvey.gpshealthsurvey;

/**
 * Created by Louis on 2/22/2015.
 */
public class Survey {
    private long surveyId;
    private String name;
    private String xmlDescription;
    private Attribute[] attributes;

    public long getSurveyId() {
        return surveyId;
    }
    public void setSurveyId(long id) {
        this.surveyId = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getXmlDescription() {
        return this.xmlDescription;
    }
    public void setXmlDescription(String xml) {
        this.xmlDescription = xml;
    }
    public Attribute[] getAttributes() {
        return attributes;
    }
    public void setAttributes(Attribute[] arr) {
        this.attributes = arr;
    }
}
