package com.gpshealthsurvey.gpshealthsurvey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Louis on 3/27/2015.
 */
public class HouseholdDataSource {

    private SQLiteDatabase database;
    private HealthSurveyDbHelper dbHelper;
    private String[] allColumns = { HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_DESCRIPTION,
            HealthSurveyDbHelper.HOUSEHOLD_COLUMN_LATITUDE, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_LONGITUDE,
            HealthSurveyDbHelper.HOUSEHOLD_COLUMN_VILLAGE, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_SURVEYXML };

    public HouseholdDataSource(Context context) {
        dbHelper = new HealthSurveyDbHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    // takes in a household object
    public void createHousehold(Household newHouse) {
        ContentValues values = new ContentValues();
        //values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID, newHouse.getHouseId());
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_DESCRIPTION, newHouse.getDescription());
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_LATITUDE, newHouse.getLatitude());
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_LONGITUDE, newHouse.getLongitude());
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_VILLAGE, newHouse.getVillageName());
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_SURVEYXML, newHouse.getVillageName());
        long insertId = database.insert(HealthSurveyDbHelper.HOUSEHOLD_TABLE_NAME, null, values);
        Cursor cursor = database.query(HealthSurveyDbHelper.HOUSEHOLD_TABLE_NAME, allColumns, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        //Household newHousehold = cursorToHousehold(cursor);
        cursor.close();
        //return newHousehold;
    }

    private Household cursorToHousehold(Cursor cursor) {
        Household household = new Household();
        household.setHouseId(cursor.getLong(0));
        household.setDescription(cursor.getString(1));
        household.setLatitude(cursor.getDouble(2));
        household.setLongitude(cursor.getDouble(3));
        household.setVillageName(cursor.getString(4));
        household.setSurveyXML(cursor.getString(5));
        return household;
    }

    public void deleteHousehold(Household household) {
        long id = household.getHouseId();
        System.out.println("Household deleted with HouseId: " + id);
        database.delete(HealthSurveyDbHelper.HOUSEHOLD_TABLE_NAME, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID
                + " = " + id, null);
    }

    // method to update a household
    public void updateHousehold(Household household) {
        long id = household.getHouseId();
        // Matching clause
        String insertWhere = HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID + "=" + Long.toString(household.houseId);

        ContentValues newValues = new ContentValues();
        newValues.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_DESCRIPTION, household.description);
        newValues.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_VILLAGE, household.villageName);
        newValues.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_SURVEYXML, household.surveyXML);
        database.update(HealthSurveyDbHelper.HOUSEHOLD_TABLE_NAME, newValues, insertWhere, null);
    }

    // create method to select all households
    public ArrayList<Household> getAllHouseholds() {
        ArrayList<Household> households = new ArrayList<Household>();

        Cursor cursor = database.query(dbHelper.HOUSEHOLD_TABLE_NAME, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Household household = cursorToHousehold(cursor);
            households.add(household);
            cursor.moveToNext();
        }
        cursor.close();;
        return households;
    }
}
