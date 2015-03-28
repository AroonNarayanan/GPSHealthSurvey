package com.gpshealthsurvey.gpshealthsurvey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Louis on 3/27/2015.
 */
public class SurveyDataSource {

    private SQLiteDatabase database;
    private HealthSurveyDbHelper dbHelper;
    private String[] allColumns = { HealthSurveyDbHelper.SURVEY_COLUMN_SURVEYID, HealthSurveyDbHelper.SURVEY_COLUMN_NAME,
            HealthSurveyDbHelper.SURVEY_COLUMN_XMLDESCRIPTION };

    public SurveyDataSource(Context context) {
        dbHelper = new HealthSurveyDbHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public Survey createProject(Survey survey) {
        ContentValues values = new ContentValues();
        values.put(HealthSurveyDbHelper.SURVEY_COLUMN_SURVEYID, survey.getSurveyId());
        values.put(HealthSurveyDbHelper.SURVEY_COLUMN_NAME, survey.getName());
        values.put(HealthSurveyDbHelper.SURVEY_COLUMN_XMLDESCRIPTION, survey.getXmlDescription());
        long insertId = database.insert(HealthSurveyDbHelper.SURVEY_TABLE_NAME, null, values);
        Cursor cursor = database.query(HealthSurveyDbHelper.SURVEY_TABLE_NAME, allColumns, HealthSurveyDbHelper.SURVEY_COLUMN_SURVEYID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Survey newSurvey = cursorToSurvey(cursor);
        cursor.close();
        return newSurvey;
    }

    private Survey cursorToSurvey(Cursor cursor) {
        Survey survey = new Survey();
        survey.setSurveyId(cursor.getLong(0));
        survey.setName(cursor.getString(1));
        survey.setXmlDescription(cursor.getString(2));
        //HOW DO WE HANDLE ATTRIBUTES FOR A SURVEY??
        return survey;
    }

    public void deleteSurvey(Survey survey) {
        long id = survey.getSurveyId();
        System.out.println("Survey deleted with HouseId: " + id);
        database.delete(HealthSurveyDbHelper.SURVEY_TABLE_NAME, HealthSurveyDbHelper.SURVEY_COLUMN_SURVEYID
                + " = " + id, null);
    }
}
