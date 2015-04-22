package com.gpshealthsurvey.gpshealthsurvey;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Aroon on 4/22/2015.
 */
public class CSVSerializer {
    public static String createCSVstring(ArrayList<Household> households) throws FileNotFoundException{
        String csv = "";
        for(Household household:households){
            //unpack XML
            csv+=Long.toString(household.houseId);
            csv+=cleanField(household.description);
            //csv+=cleanField(household.village);
            //loop over XML fields and write

            csv+="\n";
        }

        return csv;
    }

    private static String cleanField(String input){
        return input.replace(",",";");
    }
}
