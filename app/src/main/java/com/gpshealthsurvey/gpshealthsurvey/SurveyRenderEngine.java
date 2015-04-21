package com.gpshealthsurvey.gpshealthsurvey;

import android.app.ActionBar;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Aroon on 3/31/2015.
 */
public class SurveyRenderEngine {
    public static LinearLayout GetSurveyView(SurveyTemplate survey, Context context){
        //TODO: get attributes
         ArrayList<FieldTemplate> attributes = survey.fields;
        LinearLayout surveyLayout = new LinearLayout(context);
        for (FieldTemplate attribute: attributes){
            switch (attribute.field_type){
                case "text":
                    TextView text_field_label = new TextView(context);
                    text_field_label.setText(attribute.field_name);
                    EditText text_field = new EditText(context);
                    text_field.setText(attribute.field_value);
                    surveyLayout.addView(text_field_label);
                    surveyLayout.addView(text_field);
                    break;
                case "number":
                    TextView num_field_label = new TextView(context);
                    num_field_label.setText(attribute.field_name);
                    EditText num_field = new EditText(context);
                    num_field.setText(attribute.field_value);
                    surveyLayout.addView(num_field_label);
                    surveyLayout.addView(num_field);
                    break;
                //add dropdowns and checkboxes
            }
        }
        //TODO: style layout
        return surveyLayout;
    }

    public static SurveyTemplate GetSurveyFromXML(File template_file){

       Serializer serializer = new Persister();
        try {
            return serializer.read(SurveyTemplate.class,template_file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void CreateSurvey(){

    }

    public void SaveSurvey(){

    }
}
