package com.gpshealthsurvey.gpshealthsurvey;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Aroon on 4/20/2015.
 */
public class SurveyTemplate implements Serializable {

    String name;
    ArrayList<FieldTemplate> fields;

}
