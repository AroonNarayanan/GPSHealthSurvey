package com.gpshealthsurvey.gpshealthsurvey;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Aroon on 4/22/2015.
 */
public class CSVSerializer {
    public static String createCSVstring(ArrayList<Household> households) throws Exception {
        String csv = "";
        //TODO: add headers...
        for(Household household:households){
            //unpack XML
            csv+=Long.toString(household.houseId);
            csv+=",";
            csv+=cleanField(household.description);
            csv+=",";
            csv+=cleanField(household.villageName);
            csv+=",";
            csv+=Double.toString(household.latitude);
            csv+=",";
            csv+=Double.toString(household.longitude);
            //parse XML
            Serializer serializer = new Persister();
            SurveyTemplate surveyTemplate = serializer.read(SurveyTemplate.class,household.surveyXML);
            for(FieldTemplate field:surveyTemplate.fields){
                csv+=",";
                csv+=cleanField(field.field_value);
            }

            csv+="\n";
        }

        return csv;
    }

    private static String cleanField(String input){
        return input.replace(",",";");
    }
}
