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
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Created by Aroon on 3/31/2015.
 */
public class SurveyRenderEngine {
    public static LinearLayout GetSurveyView(SurveyTemplate survey, Context context){

         ArrayList<FieldTemplate> attributes = survey.fields;
        LinearLayout surveyLayout = new LinearLayout(context);
        surveyLayout.setOrientation(LinearLayout.VERTICAL);
        //ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //surveyLayout.setLayoutParams(layoutParams);
        for (FieldTemplate attribute: attributes){
            switch (attribute.field_type){
                //TODO: make these more robust and suck less
                case "text":
                    LinearLayout textView = new LinearLayout(context);
                    //ViewGroup.LayoutParams layoutParamsText = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    //textView.setLayoutParams(layoutParamsText);
                    textView.setOrientation(LinearLayout.HORIZONTAL);
                    TextView text_field_label = new TextView(context);
                    text_field_label.setText(attribute.field_name);
                    EditText text_field = new EditText(context);
                    ///text_field.setLayoutParams(layoutParamsText);
                    text_field.setText(attribute.field_value);
                    textView.addView(text_field_label);
                    textView.addView(text_field);
                    surveyLayout.addView(textView);
                    break;
                case "number":
                    LinearLayout numView = new LinearLayout(context);
                    //LinearLayout.LayoutParams layoutParamsNum = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    //numView.setLayoutParams(layoutParamsNum);
                    numView.setOrientation(LinearLayout.HORIZONTAL);
                    TextView num_field_label = new TextView(context);
                    num_field_label.setText(attribute.field_name);
                    EditText num_field = new EditText(context);
                    //num_field.setLayoutParams(layoutParamsNum);
                    num_field.setText(attribute.field_value);
                    numView.addView(num_field_label);
                    numView.addView(num_field);
                    surveyLayout.addView(numView);
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

    public static SurveyTemplate GetSurveyFromXML(String template_string){
        SurveyTemplate test = new SurveyTemplate();
        Serializer serializer = new Persister();
        try {
          test = serializer.read(SurveyTemplate.class,template_string);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public static String GetSurveyXML(LinearLayout view) throws Exception {
        ///this makes a lot of assumptions about the view being taken in
        //TODO: make this work for different field types
        ArrayList<FieldTemplate> fields = new ArrayList<FieldTemplate>();
        for(int i=0; i < view.getChildCount(); i++){
            LinearLayout childView = (LinearLayout) view.getChildAt(i);
            TextView label = (TextView) childView.getChildAt(0);
            EditText value = (EditText) childView.getChildAt(1);
            FieldTemplate field = new FieldTemplate();
            field.field_value = value.getText().toString();
            field.field_name = label.getText().toString();
            //TODO: THIS IS HARDCODED
            field.field_type = "text";
            fields.add(field);
        }
        Serializer serializer = new Persister();
        StringWriter stringWriter = new StringWriter();
        SurveyTemplate surveyTemplate = new SurveyTemplate();
        surveyTemplate.name = "";
        surveyTemplate.fields = fields;
        serializer.write(surveyTemplate,stringWriter);
        return stringWriter.toString();
    }
    public void CreateSurvey(){

    }

    public void SaveSurvey(){

    }
}
