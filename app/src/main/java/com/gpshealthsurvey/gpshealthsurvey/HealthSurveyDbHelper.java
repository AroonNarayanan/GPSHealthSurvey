package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Louis on 3/10/2015.
 */
public class HealthSurveyDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HealthSurveyDatabase.db";
    public static final String ATTRIBUTE_TABLE_NAME = "attribute";
    public static final String ATTRIBUTE_COLUMN_ID = "id";
    public static final String ATTRIBUTE_COLUMN_NAME = "name";
    public static final String ATTRIBUTE_COLUMN_TYPE = "type";
    public static final String ATTRIBUTE_COLUMN_VALUE = "value";
    public static final String ATTRIBUTE_COLUMN_SURVEYID = "surveyID";
    public static final String ATTRIBUTE_COLUMN_HOUSEHOLDID = "householdID";

    public static final String HOUSEHOLD_TABLE_NAME = "household";
    public static final String HOUSEHOLD_COLUMN_HOUSEID = "houseID";
    public static final String HOUSEHOLD_COLUMN_DESCRIPTION = "description";
    public static final String HOUSEHOLD_COLUMN_LONGITUDE = "longitude";
    public static final String HOUSEHOLD_COLUMN_LATITUDE = "latitude";

    public static final String PROJECT_TABLE_NAME = "project";
    public static final String PROJECT_COLUMN_NAME = "name";
    public static final String PROJECT_COLUMN_PROJECTID = "projectID";
    public static final String PROJECT_COLUMN_DESCRIPTION = "description";
    public static final String PROJECT_COLUMN_LATITUDE = "latitude";
    public static final String PROJECT_COLUMN_LONGITUDE = "longitude";

    public static final String SURVEY_TABLE_NAME = "survey";
    public static final String SURVEY_COLUMN_SURVEYID = "surveyID";
    public static final String SURVEY_COLUMN_NAME = "name";
    public static final String SURVEY_COLUMN_XMLDESCRIPTION = "xmldescription";


    public HealthSurveyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
        "CREATE TABLE " + ATTRIBUTE_TABLE_NAME + " (" +
        ATTRIBUTE_COLUMN_ID + " INTEGER PRIMARY KEY," +
        ATTRIBUTE_COLUMN_NAME + " TEXT," +
        ATTRIBUTE_COLUMN_TYPE + " TEXT," +
        ATTRIBUTE_COLUMN_VALUE + " TEXT," +
        ATTRIBUTE_COLUMN_SURVEYID + " TEXT," +
        ATTRIBUTE_COLUMN_HOUSEHOLDID + " TEXT, " +
        " );" +
        "CREATE TABLE " + HOUSEHOLD_TABLE_NAME + " (" +
        HOUSEHOLD_COLUMN_HOUSEID + " INTEGER PRIMARY KEY," +
        ATTRIBUTE_COLUMN_HOUSEHOLDID + " TEXT, " +
        HOUSEHOLD_COLUMN_DESCRIPTION + " TEXT," +
        HOUSEHOLD_COLUMN_LONGITUDE + " TEXT," +
        HOUSEHOLD_COLUMN_LATITUDE + " TEXT," +
        " );" +
        "CREATE TABLE " + PROJECT_TABLE_NAME + " (" +
        PROJECT_COLUMN_NAME + " INTEGER PRIMARY KEY," +
        PROJECT_COLUMN_PROJECTID + " TEXT, " +
        PROJECT_COLUMN_DESCRIPTION + " TEXT," +
        PROJECT_COLUMN_LATITUDE + " TEXT," +
        PROJECT_COLUMN_LONGITUDE + " TEXT," +
        " );" +
        "CREATE TABLE " + SURVEY_TABLE_NAME + " (" +
        SURVEY_COLUMN_SURVEYID + " INTEGER PRIMARY KEY," +
        SURVEY_COLUMN_NAME + " TEXT, " +
        SURVEY_COLUMN_XMLDESCRIPTION + " TEXT," +
        " );"
        );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS attribute, household, project, survey");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
