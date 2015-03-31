package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aroon on 3/31/2015.
 */
public class SurveyRenderEngine {
    public LinearLayout GetSurveyView(Survey survey, Context context){
        //TODO: get attributes
         Attribute[] attributes = survey.getAttributes();
        LinearLayout surveyLayout = new LinearLayout(context);
        for (Attribute attribute: attributes){
            switch (attribute.getType()){
                case "text":
                    TextView text_field_label = new TextView(context);
                    text_field_label.setText(attribute.getName());
                    EditText text_field = new EditText(context);
                    text_field.setText(attribute.getValue());
                    surveyLayout.addView(text_field_label);
                    surveyLayout.addView(text_field);
                    break;
                case "number":
                    TextView num_field_label = new TextView(context);
                    num_field_label.setText(attribute.getName());
                    EditText num_field = new EditText(context);
                    num_field.setText(attribute.getValue());
                    surveyLayout.addView(num_field_label);
                    surveyLayout.addView(num_field);
                    break;
                //add dropdowns and checkboxes
            }
        }
        //TODO: style layout
        return surveyLayout;
    }

    public void CreateSurvey(){

    }

    public void SaveSurvey(){

    }

    public ArrayList<String> GetSurveyTemplates(){

        return null;
    }
}
